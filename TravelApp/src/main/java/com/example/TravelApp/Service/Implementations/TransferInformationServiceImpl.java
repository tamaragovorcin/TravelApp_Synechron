package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.EmailDTO;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TransferDetailsReserveDTO;
import com.example.TravelApp.DTOs.TransferSearchDTO;
import com.example.TravelApp.Helpers.SerializerAndDeserializer;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Mapper.EmailMapper;
import com.example.TravelApp.Mapper.TransferDetailsMapper;
import com.example.TravelApp.Model.TransferDetails;
import com.example.TravelApp.Repository.TransferDetailsRepository;
import com.example.TravelApp.Service.Interfaces.ITransferDetailsService;
import com.example.TravelApp.Service.Interfaces.ITransferInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.time.LocalDate;
import java.util.*;

@Service
public class TransferInformationServiceImpl implements ITransferInformationService, Comparable {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private TransferDetailsMapper transferDetailsMapper;

    @Autowired
    private TransferDetailsRepository transferDetailsRepository;

    @Autowired
    private ITransferDetailsService transferDetailsService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public Set<TransferDetailsDTO> findBasicTransferInformation(String departureDateString, String arrivalDateString) {
        List<TransferDetails> transferDetailsList = (departureDateString != null && arrivalDateString != null) ?
                getTransferInfoWithDates(departureDateString, arrivalDateString) : getTransferInfoNoDates();

        Set<TransferDetailsDTO> transferDetailsDTOS = new HashSet<>();
        for (TransferDetails transferDetails : transferDetailsList) {

                Set<TransferDetails> foundTransfers = transferDetailsService.findByTransferReqID(transferDetails.getTravelReqID());
                if(foundTransfers.size() == 1 || foundTransfers.size() == 2){
                    transferDetailsDTOS.add(transferDetailsMapper.transferDetailsToDTO(transferDetails));
                }else if(foundTransfers.size() > 2){
                    for(TransferDetails t : foundTransfers){
                        if(t.getUpdateDate() != null){
                            List<TransferDetails> transferList = new ArrayList<>();
                            for(TransferDetails transfer : foundTransfers){
                                if(transfer.getUpdateDate() != null){
                                    transferList.add(transfer);
                                }
                            }
                            TransferDetails latestUpdated = findLatestUpdatedTransfer(transferList);
                            TransferDetailsDTO dto = transferDetailsMapper.transferDetailsToDTO(latestUpdated);
                            boolean exists = false;
                            for(TransferDetailsDTO transferDTO : transferDetailsDTOS){
                                if(dto.getId() == transferDTO.getId()){
                                    exists = true;
                                    break;
                                }
                            }
                            if(!exists){
                                transferDetailsDTOS.add(dto);
                            }else{
                            }
                        }
                    }
                }
        }
        return transferDetailsDTOS;
    }

    @Override
    public List<TransferDetails> getTransferInfoNoDates() {
        return transferDetailsRepository.findAll();
    }

    @Override
    public List<TransferDetails> getTransferInfoWithDates(String departureDateString, String arrivalDateString) {
        LocalDate departureDate = TimeAndDateParser.parseToLocalDate(departureDateString);
        LocalDate arrivalDate = TimeAndDateParser.parseToLocalDate(arrivalDateString);
        return transferDetailsRepository.findAllTransferDetailsBetweenDates(departureDate, arrivalDate);
    }

    @Override
    public Set<TransferDetailsDTO> reserveTransfer(Set<TransferDetailsReserveDTO> transferDetailsReserveDTOS) {
        Set<TransferDetailsDTO> transferDetailsDTOs = new HashSet<TransferDetailsDTO>();
        for(TransferDetailsReserveDTO transferDetailsReserveDTO : transferDetailsReserveDTOS){
            TransferDetails transferDetails = transferDetailsRepository.findById(transferDetailsReserveDTO.getId()).orElse(null);
            if(transferDetails != null){
                transferDetails.setReservationDate(TimeAndDateParser.parseDate(transferDetailsReserveDTO.getCurrentDate()));
                transferDetails.setReservationTime(TimeAndDateParser.parseTime(transferDetailsReserveDTO.getCurrentTime()));
                if(transferDetailsReserveDTO.getTransferDate() != null) {
                    transferDetails.setTransferDate(TimeAndDateParser.parseDate(transferDetailsReserveDTO.getTransferDate()));
                }
                if(transferDetailsReserveDTO.getTransferTime() != null){
                    transferDetails.setTransferTime(TimeAndDateParser.parseTime(transferDetailsReserveDTO.getTransferTime()));
                }
                if(transferDetailsReserveDTO.getTransferCity() != null){
                    transferDetails.setTransferCity(transferDetailsReserveDTO.getTransferCity());
                }
                if(transferDetailsReserveDTO.getTransferAddress() != null){
                    transferDetails.setTransferAddress(transferDetailsReserveDTO.getTransferAddress());
                }
                transferDetails.setAdditionalComments(transferDetailsReserveDTO.getAdditionalComments());
                transferDetailsRepository.save(transferDetails);
                transferDetailsDTOs.add(transferDetailsMapper.transferDetailsToDTO(transferDetails));
                try {
                    TransferDetailsDTO transferDetailsDTO = transferDetailsMapper.transferDetailsToDTO(transferDetails);
                    EmailDTO emailDTO = emailMapper.emailDTOFromTransferDTO(transferDetailsDTO);
                    emailDTO.setMessage("transferReserved");
                    String messageObj = SerializerAndDeserializer.toString(emailDTO);
                    jmsTemplate.convertAndSend(queue, messageObj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return transferDetailsDTOs;
    }

    @Override
    public Set<TransferDetailsDTO> searchTransfers(TransferSearchDTO transferSearchDTO) {
        Set<TransferDetailsDTO> allTransfers = findBasicTransferInformation(transferSearchDTO.getDepartureDateString(), transferSearchDTO.getArrivalDateString());
        Set<TransferDetailsDTO> retVal = new HashSet<>();
        int searchNum = findNumberOfParameters(transferSearchDTO);
        for(TransferDetailsDTO transferDetails : allTransfers){
            int counter = findNumberOfMatchingParameters(transferDetails, transferSearchDTO);

            if(counter == searchNum){
                retVal.add(transferDetails);
            }
        }
        return retVal;
    }

    private int findNumberOfParameters(TransferSearchDTO transferSearchDTO){
        int searchNum = 1;
        if(transferSearchDTO.getName() != "" && transferSearchDTO.getName() != null){
            searchNum++;
        }if(transferSearchDTO.getSurname() != "" && transferSearchDTO.getSurname() != null){
            searchNum++;
        }if(transferSearchDTO.getDepartingFrom() != "" && transferSearchDTO.getDepartingFrom() != null){
            searchNum++;
        }if(transferSearchDTO.getArriveTo() != "" && transferSearchDTO.getArriveTo() != null){
            searchNum++;
        }if(transferSearchDTO.getDepartingCountry() != "" && transferSearchDTO.getDepartingCountry() != null){
            searchNum++;
        }if(transferSearchDTO.getArrivalCountry() != "" && transferSearchDTO.getArrivalCountry() != null){
            searchNum++;
        }
        return searchNum;
    }

    private int findNumberOfMatchingParameters(TransferDetailsDTO transferDetails,TransferSearchDTO transferSearchDTO){
        int counter = 0;
        if(transferSearchDTO.getName() != "" && transferSearchDTO.getName() != null){
            if(transferDetails.getEmployee().getFirstName().toLowerCase().contains(transferSearchDTO.getName().toLowerCase(Locale.ROOT))){
                counter++;
            }
        }if(transferSearchDTO.getSurname() != "" && transferSearchDTO.getSurname() != null){
            if(transferDetails.getEmployee().getLastName().toLowerCase().contains(transferSearchDTO.getSurname().toLowerCase(Locale.ROOT))){
                counter++;
            }
        }if(transferSearchDTO.getDepartingFrom() != "" && transferSearchDTO.getDepartingFrom() != null){
            if(transferDetails.getDepartFrom().toLowerCase().contains(transferSearchDTO.getDepartingFrom().toLowerCase(Locale.ROOT))){
                counter++;
            }
        }if(transferSearchDTO.getArriveTo() != "" && transferSearchDTO.getArriveTo() != null){
            if(transferDetails.getArriveTo().toLowerCase().contains(transferSearchDTO.getArriveTo().toLowerCase(Locale.ROOT))){
                counter++;
            }
        }
        if(transferSearchDTO.getDepartingCountry() != "" && transferSearchDTO.getDepartingCountry() != null){
            if(transferDetails.getDepartingCountry().toLowerCase().contains(transferSearchDTO.getDepartingCountry().toLowerCase(Locale.ROOT))){
                counter++;
            }
        }if(transferSearchDTO.getArrivalCountry() != "" && transferSearchDTO.getArrivalCountry() != null){
            if(transferDetails.getArrivalCountry().toLowerCase().contains(transferSearchDTO.getArrivalCountry().toLowerCase(Locale.ROOT))){
                counter++;
            }
        }
        if(transferSearchDTO.getReservationType().equals("All")){
            counter++;
        }else{
            if(transferSearchDTO.getReservationType().equals("Reserved") && transferDetails.getReservationDate()!=null){
                counter++;
            }else if(transferSearchDTO.getReservationType().equals("Nonreserved") && transferDetails.getReservationDate()==null){
                counter++;
            }
        }
        return counter;
    }

    private TransferDetails findLatestUpdatedTransfer(List<TransferDetails> transferDetailsList){
        Collections.sort(transferDetailsList, Comparator.comparing(TransferDetails::getUpdateDate));
        return transferDetailsList.get(transferDetailsList.size()-1);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
