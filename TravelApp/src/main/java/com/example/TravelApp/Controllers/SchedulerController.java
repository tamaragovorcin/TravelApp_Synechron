package com.example.TravelApp.Controllers;

import com.example.TravelApp.Service.Interfaces.IScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/api/scheduler" , produces = MediaType.APPLICATION_JSON_VALUE)
public class SchedulerController {

    @Autowired
    IScheduleService scheduleService;

   // @Scheduled(cron = "*/10 * * * * *")
    public void reserveTransfer() throws JsonProcessingException {
        scheduleService.reserveTransfer();
    }

    // @Scheduled(cron = "*/10 * * * * *") //ovo ce se raditi svaki ponedeljak
    public void advancePayment() throws JsonProcessingException {
        scheduleService.advancePayment();
    }

    // @Scheduled(cron = "*/10 * * * * *")
    public void payoff() throws JsonProcessingException {
        scheduleService.payoff();
    }
}
