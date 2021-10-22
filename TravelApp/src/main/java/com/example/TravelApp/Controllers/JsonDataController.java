package com.example.TravelApp.Controllers;

import com.example.TravelApp.Service.Interfaces.IJsonDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/api/json" , produces = MediaType.APPLICATION_JSON_VALUE)
public class JsonDataController {
    @Autowired
    IJsonDataService jsonDataService;

    @Scheduled(cron = "*/10 * * * * *")
    public void getAndSaveTravelData() throws JsonProcessingException {
       jsonDataService.getTravelData();
    }

}
