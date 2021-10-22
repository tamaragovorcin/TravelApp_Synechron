package com.example.TravelApp.Controllers;

import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Service.Interfaces.ITransferDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/transfer-details" , produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferDetailsController {

    @Autowired
    private ITransferDetailsService transferDetailsService;

    @PostMapping(value = "/update")
    public ResponseEntity<TransferDetailsDTO> update(@RequestBody TransferDetailsDTO transferDetailsDTO){
        TransferDetailsDTO transferDTO =  transferDetailsService.update(transferDetailsDTO);
        if(transferDTO != null){
            return new ResponseEntity<>(transferDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<TransferDetailsDTO>> findAll(){
        List<TransferDetailsDTO> transferDetailsDTOS=transferDetailsService.findAll();
        return new ResponseEntity<>(transferDetailsDTOS, HttpStatus.OK);
    }

}
