package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.EmailDTO;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Helpers.SerializerAndDeserializer;
import com.example.TravelApp.Mapper.EmailMapper;
import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Service.ActiveMQ.Consumer;
import com.example.TravelApp.Service.Interfaces.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.time.LocalDate;
import java.util.*;

@Service
public class ScheduleService implements IScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    TravelDetailsServiceImpl travelDetailsService;

    @Autowired
    ITransferInformationService transferInformationService;

    @Autowired
    IDecisionForABusinessTripService decisionForABusinessTripService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public void reserveTransfer() {

        LocalDate inAWeek = LocalDate.now().plusDays(7);

        List<TravelDetailsDTO> travelDetailsDTOListNotReserved = new ArrayList<>();
        List<TravelDetailsDTO> travelDetailsDTOListCheck = new ArrayList<>();

        for (TravelDetailsDTO travelDetailsDTO : travelDetailsService.updateTravelDetails(travelDetailsService.findAll())) {
            if (LocalDate.parse(travelDetailsDTO.getDepartureDate()).equals(inAWeek) || LocalDate.parse(travelDetailsDTO.getArrivalDate()).equals(inAWeek)) {

                for (TransferDetailsDTO transferDetailsDTO : transferInformationService.findBasicTransferInformation(null, null)) {

                    if (transferDetailsDTO.getTravelReqID().equals(travelDetailsDTO.getTravelReqID()) && (transferDetailsDTO.getReservationDate() == null || transferDetailsDTO.getReservationTime() == null)) {

                        travelDetailsDTOListNotReserved.add(travelDetailsDTO);

                    }

                    if (transferDetailsDTO.getTravelReqID().equals(travelDetailsDTO.getTravelReqID()) && transferDetailsDTO.getReservationDate() != null && transferDetailsDTO.getReservationTime() != null) {

                        travelDetailsDTOListCheck.add(travelDetailsDTO);

                    }
                }
            }
        }
        if(travelDetailsDTOListNotReserved.size()!=0) {
            EmailDTO emailDTO = emailMapper.emailDtoFromTravelDTOs(travelDetailsDTOListNotReserved);
            emailDTO.setMessage("reserveTransferForUpcomingTrip");
            try {
                String messageObj = SerializerAndDeserializer.toString(emailDTO);
                jmsTemplate.convertAndSend(queue, messageObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(travelDetailsDTOListCheck.size()!=0) {
            EmailDTO emailDTO = emailMapper.emailDtoFromTravelDTOs(travelDetailsDTOListNotReserved);
            emailDTO.setMessage("checkReserveTransferForUpcomingTrip");
            try {
                String messageObj = SerializerAndDeserializer.toString(emailDTO);
                jmsTemplate.convertAndSend(queue, messageObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void advancePayment() {
        LocalDate inAWeek = LocalDate.now().plusDays(7);

        List<DecisionForABusinessTrip> decisionForABusinessTrips = new ArrayList<>();

        for (TravelDetailsDTO travelDetailsDTO : travelDetailsService.updateTravelDetails(travelDetailsService.findAll())) {
            if (LocalDate.parse(travelDetailsDTO.getDepartureDate()).equals(inAWeek)) {

                for (DecisionForABusinessTrip decisionForABusinessTrip : decisionForABusinessTripService.findAll()) {

                    if (decisionForABusinessTrip.getTravelReqID().equals(travelDetailsDTO.getTravelReqID()) && decisionForABusinessTrip.getAdvancePaymentEUR()!=null) {
                        decisionForABusinessTrips.add(decisionForABusinessTrip);

                    }
                }
            }
        }

        if(decisionForABusinessTrips.size()!=0){
            EmailDTO emailDTO = emailMapper.emailDtoFromDecisionForABusinessTrip(decisionForABusinessTrips);
            emailDTO.setMessage("advancePayment");
            try {
                String messageObj = SerializerAndDeserializer.toString(emailDTO);
                jmsTemplate.convertAndSend(queue, messageObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void payoff() {
        LocalDate today = LocalDate.now();
        LocalDate in3Days = LocalDate.now().plusDays(4);

        List<TravelDetailsDTO> arrivalsSoon = new ArrayList<>();

        for (TravelDetailsDTO travelDetailsDTO : travelDetailsService.updateTravelDetails(travelDetailsService.findAll())) {
            if (LocalDate.parse(travelDetailsDTO.getArrivalDate()).isAfter(today) && LocalDate.parse(travelDetailsDTO.getArrivalDate()).isBefore(in3Days) && !travelDetailsDTO.getMailSent()) {

                arrivalsSoon.add(travelDetailsDTO);

            }
        }

        if(arrivalsSoon.size()!=0) {
            EmailDTO emailDTO = emailMapper.emailDtoFromTravelDTOs(arrivalsSoon);
            emailDTO.setMessage("payoff");
            try {
                String messageObj = SerializerAndDeserializer.toString(emailDTO);
                jmsTemplate.convertAndSend(queue, messageObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
