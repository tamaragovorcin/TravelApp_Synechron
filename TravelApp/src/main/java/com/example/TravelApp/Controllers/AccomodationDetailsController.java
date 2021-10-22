package com.example.TravelApp.Controllers;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Service.Interfaces.IAccommodationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping( value = "/api/accomodation" , produces = MediaType.APPLICATION_JSON_VALUE)
public class AccomodationDetailsController {

    @Autowired
    private IAccommodationDetailsService accomodationDetailsService;

    @GetMapping(value = "/all")
    public ResponseEntity<Set<AccommodationDetailsDTO>> findAll(){
        Set<AccommodationDetailsDTO> accommodationDetailsDTOSet = accomodationDetailsService.findAll();
        return new ResponseEntity<>(accommodationDetailsDTOSet, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<AccommodationDetailsDTO> create(@RequestBody AccommodationDetailsDTO accommodationDetailsDTO){
        AccommodationDetailsDTO accommodationDetails = accomodationDetailsService.save(accommodationDetailsDTO);
        return new ResponseEntity<>(accommodationDetails, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Set<TravelDetailsDTO>> update(@RequestBody AccommodationDetailsDTO accommodationDetailsDTO){


        Set<TravelDetailsDTO> detailsDTO =  accomodationDetailsService.update(accommodationDetailsDTO);
        return new ResponseEntity<>(detailsDTO, HttpStatus.OK);
    }


}