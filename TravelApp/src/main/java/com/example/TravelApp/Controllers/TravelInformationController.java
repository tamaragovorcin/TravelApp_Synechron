package com.example.TravelApp.Controllers;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.DTOs.TravelInfoSearchDTO;
import com.example.TravelApp.Service.Interfaces.ITravelInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping( value = "/api/travel-info" , produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelInformationController {
    @Autowired
    ITravelInformationService travelInformationService;

    @GetMapping(path ={ "/{startDate}/{endDate}", "/"})
    public ResponseEntity<Set<TravelDetailsDTO>> findBasicTravelInformation(@PathVariable(required = false) String startDate,@PathVariable(required = false) String endDate) {
        Set<TravelDetailsDTO> travelInformationDTOS=travelInformationService.findBasicTravelInformation(startDate,endDate);
        return new ResponseEntity<>(travelInformationDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<Set<TravelDetailsDTO>> findBasicTravelInformationBySearch(@RequestBody TravelInfoSearchDTO travelInfoSearchDTO) {

        Set<TravelDetailsDTO> travelInformationDTOS=travelInformationService.findBasicTravelInformationBySearch(travelInfoSearchDTO);
        return new ResponseEntity<>(travelInformationDTOS, HttpStatus.OK);
    }


}
