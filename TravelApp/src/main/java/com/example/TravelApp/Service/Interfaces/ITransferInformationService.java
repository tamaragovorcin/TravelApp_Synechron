package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TransferDetailsReserveDTO;
import com.example.TravelApp.DTOs.TransferSearchDTO;
import com.example.TravelApp.Model.TransferDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ITransferInformationService {
    Set<TransferDetailsDTO> findBasicTransferInformation(String startDate, String endDate);
    List<TransferDetails> getTransferInfoNoDates();
    List<TransferDetails> getTransferInfoWithDates(String departureDateString, String arrivalDateString);
    Set<TransferDetailsDTO> reserveTransfer(Set<TransferDetailsReserveDTO> transferDetailsReserveDTO);
    Set<TransferDetailsDTO> searchTransfers(TransferSearchDTO transferSearchDTO);
}
