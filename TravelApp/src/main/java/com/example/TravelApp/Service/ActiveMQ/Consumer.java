package com.example.TravelApp.Service.ActiveMQ;

import com.example.TravelApp.DTOs.EmailDTO;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.Helpers.SerializerAndDeserializer;
import com.example.TravelApp.Model.TransferDetails;
import com.example.TravelApp.Service.Implementations.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private EmailServiceImpl emailService;

    @JmsListener(destination = "travel-app-queue")
    public void consumeMessage(String message) throws IOException, ClassNotFoundException {
        EmailDTO emailDTO = ( EmailDTO ) SerializerAndDeserializer.fromString( message );
        if(emailDTO.getMessage().equals("transferReserved")){
            emailService.sendReservationEmail(emailDTO);
        }else if(emailDTO.getMessage().equals("reserveTransferForUpcomingTrip")){
            emailService.reserveTransferForUpcomingTrip(emailDTO);
        }else if(emailDTO.getMessage().equals("checkReserveTransferForUpcomingTrip")){
            emailService.reserveTransporationForUpcomingTrip(emailDTO);
        }else if(emailDTO.getMessage().equals("advancePayment")){
            emailService.advancePaymentNotification(emailDTO);
        }else if(emailDTO.getMessage().equals("payoff")){
            emailService.payOffNotification(emailDTO);
        }else if(emailDTO.getMessage().equals("sendDecisionAndOrderEmail")){
            emailService.sendDecisionAndOrderEmail(emailDTO);
        }else if(emailDTO.getMessage().equals("sendCalculationEmail")){
            emailService.sendCalculationEmail(emailDTO);
        }
    }
}
