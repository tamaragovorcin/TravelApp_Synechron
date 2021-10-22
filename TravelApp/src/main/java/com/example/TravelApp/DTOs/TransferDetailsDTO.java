package com.example.TravelApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDetailsDTO implements Serializable {

    private long id;
    private String travelReqID;
    private String flightId;
    private String reservationDate;
    private String reservationTime;
    private String departFrom;
    private String arriveTo;
    private String departureDate;
    private EmployeeDTO employee;
    private String arrivalTime;
    private String arrivalDate;
    private String departureTime;
    private String departingCountry;
    private String arrivalCountry;
    private String additionalComments;
    private String previousId;
    private String updateDate;
    private String updateTime;
    private String transferDate;
    private String transferTime;
    private String transferCity;
    private String transferAddress;
    private String message;
}
