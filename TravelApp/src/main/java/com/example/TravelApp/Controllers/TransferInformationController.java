package com.example.TravelApp.Controllers;

import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TransferDetailsReserveDTO;
import com.example.TravelApp.DTOs.TransferSearchDTO;
import com.example.TravelApp.Service.Interfaces.ITransferInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping (value = "/api/transfer-info", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferInformationController {

    @Autowired
    private ITransferInformationService transferDetailsService;

    @GetMapping(path ={ "/{startDate}/{endDate}", "/"})
    public ResponseEntity<Set<TransferDetailsDTO>> findBasicTravelInformation(@PathVariable(required = false) String startDate, @PathVariable(required = false) String endDate) {
        Set<TransferDetailsDTO> transferInformationDTOS = transferDetailsService.findBasicTransferInformation(startDate,endDate);
        return new ResponseEntity<>(transferInformationDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/reserve")
    public ResponseEntity<Set<TransferDetailsDTO>> reserveTransfer(@RequestBody Set<TransferDetailsReserveDTO> transferDetailsReserveDTO){
        Set<TransferDetailsDTO> transferDTOs = transferDetailsService.reserveTransfer(transferDetailsReserveDTO);
        if(transferDTOs.size() == transferDetailsReserveDTO.size()){
            return new ResponseEntity<>(transferDTOs, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/search")
    public ResponseEntity<Set<TransferDetailsDTO>> searchTransfers(@RequestBody TransferSearchDTO transferSearchDTO){
        Set<TransferDetailsDTO> transferDTOs = transferDetailsService.searchTransfers(transferSearchDTO);
        if(!transferDTOs.isEmpty()){
            return new ResponseEntity<>(transferDTOs, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
