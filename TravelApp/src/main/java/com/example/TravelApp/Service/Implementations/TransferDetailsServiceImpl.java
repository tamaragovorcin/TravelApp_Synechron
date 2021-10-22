package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeTravelDetail;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Mapper.TransferDetailsMapper;
import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Model.TransferDetails;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Repository.EmployeeRepository;
import com.example.TravelApp.Repository.TransferDetailsRepository;
import com.example.TravelApp.Service.Interfaces.ITransferDetailsService;
import com.example.TravelApp.Service.Interfaces.ITravelDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TransferDetailsServiceImpl implements ITransferDetailsService {

    @Autowired
    private ITravelDetailsService travelDetailsService;

    @Autowired
    private TransferDetailsMapper transferDetailsMapper;

    @Autowired
    private TransferDetailsRepository transferDetailsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void parseTransferDetails(List<JsonEmployeeTravelDetail> employee_travel_details) {
        for (JsonEmployeeTravelDetail jsonEmployeeTravelDetail: employee_travel_details) {
            Set<TransferDetails> transferDetailsByTravelReqID = findByTransferReqID(String.valueOf(jsonEmployeeTravelDetail.getTravelReqID()));
            if(transferDetailsByTravelReqID.size() == 0 || !alreadyExists(transferDetailsByTravelReqID, jsonEmployeeTravelDetail)) {
                TransferDetails transferDetails = transferDetailsMapper.jsonDetailsToTransferDetails(jsonEmployeeTravelDetail);
                transferDetailsRepository.save(transferDetails);
            }
        }
    }

    @Override
    public Set<TransferDetails> findByTransferReqID(String id) {
        Set<TransferDetails> transferDetails = this.transferDetailsRepository.findByTransferReqID(id);
        return transferDetails;
    }

    @Override
    public TransferDetailsDTO update(TransferDetailsDTO transferDetailsDTO) {
        TransferDetails transferDetails = transferDetailsRepository.findById(transferDetailsDTO.getId()).orElse(null);
        if(transferDetails != null){
            TransferDetails transferDetailsUpdated = transferDetailsMapper.transferDetailsDTOToTransferDetails(transferDetailsDTO);
            transferDetailsUpdated.setPreviousId(String.valueOf(transferDetails.getId()));
            transferDetailsUpdated.setUpdateDate(TimeAndDateParser.currentDate());
            transferDetailsUpdated.setUpdateTime(TimeAndDateParser.currentTime());
            TransferDetails t = transferDetailsRepository.save(transferDetailsUpdated);
            return transferDetailsMapper.transferDetailsToDTO(t);
        }
        return null;
    }

    @Override
    public List<TransferDetailsDTO> findAll() {
        List<TransferDetails> transferDetailsList=  transferDetailsRepository.findAll();
        List<TransferDetailsDTO> transferDetailsDTOs = new ArrayList<>();
        for(TransferDetails transferDetails : transferDetailsList){
            transferDetailsDTOs.add(transferDetailsMapper.transferDetailsToDTO(transferDetails));
        }
        return transferDetailsDTOs;
    }

    private boolean alreadyExists(Set<TransferDetails> transferDetailsByTravelReqID, JsonEmployeeTravelDetail jsonEmployeeTravelDetail){
        boolean saveTrip = false;
        for(TransferDetails transferDetails : transferDetailsByTravelReqID){
            if(transferDetails.getDepartureDate().isEqual(TimeAndDateParser.parseDate(jsonEmployeeTravelDetail.getDepartureDate()))){
                if(transferDetails.getDepartureTime().equals(TimeAndDateParser.parseTime(jsonEmployeeTravelDetail.getDepartureTime()))){
                    saveTrip = true;
                    break;
                }
            }
        }
        return saveTrip;
    }
}
