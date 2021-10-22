package com.example.TravelApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO implements Serializable {

    private Long id;
    private List<Long> ids;
    private EmployeeDTO employee;
    private String departFrom;
    private String reservationDate;
    private String reservationTime;
    private List<String> travelReqIDList;
    private List<String> departureDateList;
    private List<String> departureTimeList;
    private List<String> departFromList;
    private List<String> arrivalTimeList;
    private List<String> arrivalDateList;
    private List<String> arrivalCountryList;
    private String message;
    private List<Double> advancePaymentList;
    private List<String> employeeFullNameList;
    private String filepath;
    private String travelReqId;

}


