package com.example.TravelApp.Mapper;

import com.example.TravelApp.DTOs.EmailDTO;
import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Model.DecisionForABusinessTrip;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailMapper {

    public EmailDTO emailDTOFromTransferDTO(TransferDetailsDTO transferDetailsDTO){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setId(transferDetailsDTO.getId());
        emailDTO.setEmployee(transferDetailsDTO.getEmployee());
        emailDTO.setDepartFrom(transferDetailsDTO.getDepartFrom());
        emailDTO.setReservationDate(transferDetailsDTO.getReservationDate());
        emailDTO.setReservationTime(transferDetailsDTO.getReservationTime());
        return emailDTO;
    }

    public EmailDTO emailDtoFromDecisionForABusinessTrip(List<DecisionForABusinessTrip> decisionForABusinessTrip){
        return initializeEmailDTOFromDecision(decisionForABusinessTrip);
    }

    public EmailDTO emailDtoFromTravelDTOs(List<TravelDetailsDTO> travelDetailsDTOs){
        return initializeEmailDTO(travelDetailsDTOs);
    }

    private EmailDTO initializeEmailDTO(List<TravelDetailsDTO> travelDetailsDTOs){
        EmailDTO emailDTO = new EmailDTO();
        List<Long> idsTemp = new ArrayList<>();
        List<String> travelReqIDListTemp = new ArrayList<>();
        List<String> departureDateListTemp = new ArrayList<>();
        List<String> departureTimeListTemp = new ArrayList<>();
        List<String> departFromListTemp = new ArrayList<>();
        List<String> arrivalTimeListTemp = new ArrayList<>();
        List<String> arrivalDateListTemp = new ArrayList<>();
        List<String> arrivalCountryListTemp = new ArrayList<>();
        for(TravelDetailsDTO travelDetailsDTO : travelDetailsDTOs){
            idsTemp.add(travelDetailsDTO.getId());
            travelReqIDListTemp.add(travelDetailsDTO.getTravelReqID());
            departureDateListTemp.add(travelDetailsDTO.getDepartureDate());
            departureTimeListTemp.add(travelDetailsDTO.getDepartureTime());
            departFromListTemp.add(travelDetailsDTO.getDepartFrom());
            arrivalTimeListTemp.add(travelDetailsDTO.getArrivalTime());
            arrivalDateListTemp.add(travelDetailsDTO.getArrivalDate());
            arrivalCountryListTemp.add(travelDetailsDTO.getArrivalCountry());
        }
        emailDTO.setIds(idsTemp);
        emailDTO.setTravelReqIDList(travelReqIDListTemp);
        emailDTO.setDepartureDateList(departureDateListTemp);
        emailDTO.setDepartFromList(departFromListTemp);
        emailDTO.setDepartureTimeList(departureTimeListTemp);
        emailDTO.setArrivalCountryList(arrivalCountryListTemp);
        emailDTO.setArrivalDateList(arrivalDateListTemp);
        emailDTO.setArrivalTimeList(arrivalTimeListTemp);
        return emailDTO;
    }

    private EmailDTO initializeEmailDTOFromDecision(List<DecisionForABusinessTrip> decisionsForABusinessTrip){
        EmailDTO emailDTO = new EmailDTO();
        List<String> travelReqIDListTemp = new ArrayList<>();
        List<String> employeeListTemp = new ArrayList<>();
        List<Double> advancePaymentListTemp = new ArrayList<>();
        for(DecisionForABusinessTrip decisionForABusinessTrip : decisionsForABusinessTrip){
            travelReqIDListTemp.add(decisionForABusinessTrip.getTravelReqID());
            employeeListTemp.add(decisionForABusinessTrip.getEmployeeFullName());
            advancePaymentListTemp.add(decisionForABusinessTrip.getAdvancePaymentEUR());
        }
        emailDTO.setTravelReqIDList(travelReqIDListTemp);
        emailDTO.setEmployeeFullNameList(employeeListTemp);
        emailDTO.setAdvancePaymentList(advancePaymentListTemp);
        return emailDTO;
    }

    public EmailDTO emailDtoFromFilepath(String filepath) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setFilepath(filepath);
        return emailDTO;
    }
    public EmailDTO emailDtoFromFilepathAndTravelReqId(String filepath, String travelReqId) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setFilepath(filepath);
        emailDTO.setTravelReqId(travelReqId);
        return emailDTO;
    }
}
