package com.example.TravelApp.Controllers;

import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Service.Interfaces.IDecisionForABusinessTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/api/decision" , produces = MediaType.APPLICATION_JSON_VALUE)
public class DecisionForABusinessTripController {

    @Autowired
    IDecisionForABusinessTripService decisionForABusinessTripService;

    @GetMapping(value = "/file/{decisionId}")
    public ResponseEntity findExelFile(@PathVariable String decisionId){
        byte[] resource = decisionForABusinessTripService.exportFileByDecisionId(decisionId);
        return ResponseEntity.ok()
                .contentLength(resource.length)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }

    @GetMapping(value = "/calculation/{travelReqId}")
    public ResponseEntity createCalculation(@PathVariable String travelReqId) {
        byte[] resource = decisionForABusinessTripService.exportFileWithCalculationByTravelReqId(travelReqId);
        return ResponseEntity.ok()
                .contentLength(resource.length)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }

    @GetMapping(value = "/{travelReqId}")
    public ResponseEntity getDecisionByTravelReqId(@PathVariable String travelReqId){
        DecisionForABusinessTrip decision = decisionForABusinessTripService.findByTravelReqID(travelReqId);
        return new ResponseEntity<>(decision, HttpStatus.OK);

    }

}
