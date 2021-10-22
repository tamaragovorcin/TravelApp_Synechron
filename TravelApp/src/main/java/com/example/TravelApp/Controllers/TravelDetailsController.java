package com.example.TravelApp.Controllers;

import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Service.Interfaces.ITravelDetailsService;
import org.mozilla.javascript.EcmaError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping( value = "/api/travel-details" , produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelDetailsController {

    @Autowired
    ITravelDetailsService travelDetailsService;

    @GetMapping(value = "/all")
    public ResponseEntity<Set<TravelDetailsDTO>> findAll(){
        Set<TravelDetailsDTO> travelDetailsDTOS=travelDetailsService.findAll();
        return new ResponseEntity<>(travelDetailsDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<TravelDetailsDTO> findById(@PathVariable long id){
        TravelDetailsDTO travelDetailsDTO = travelDetailsService.findById(id);
        return new ResponseEntity<>(travelDetailsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<TravelDetailsDTO> create(@RequestBody TravelDetailsDTO travelDetailsDTO){
        TravelDetailsDTO detailsDTO = travelDetailsService.save(travelDetailsDTO);
        return new ResponseEntity<>(detailsDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Set<TravelDetailsDTO>> update(@RequestBody TravelDetailsDTO travelDetailsDTO){

        Set<TravelDetailsDTO> detailsDTO = travelDetailsService.update(travelDetailsDTO);
        if(detailsDTO != null){
            return new ResponseEntity<>(detailsDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        try {
            travelDetailsService.delete(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(id,HttpStatus.BAD_REQUEST);
        }
    }

}