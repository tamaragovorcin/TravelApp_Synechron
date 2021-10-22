package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeTravelDetail;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.Model.TransferDetails;
import com.example.TravelApp.Model.TravelDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ITransferDetailsService {

    void parseTransferDetails(List<JsonEmployeeTravelDetail> employee_travel_details);
    Set<TransferDetails> findByTransferReqID(String id);
    TransferDetailsDTO update(TransferDetailsDTO transferDetailsDTO);

    List<TransferDetailsDTO> findAll();
}
