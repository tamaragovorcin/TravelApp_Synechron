package com.example.TravelApp.DTOs.JsonTravelDataDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class    JsonEmployeeTravelDetail {


    public String departFrom;
    public String arriveTo;
    public String departureDate;
    public String travelReqID;
    public String accountName;
    public String approvedBy;
    public String employeeID;
    public String visaDetail;
    public String tripPaidBy;
    public String arrivalTime;
    public String arrivalDate;
    public String departureTime;
    public String departingCountry;
    public String arrivalCountry;
    public String addtionalComments;
    public String bookingPurpose;
}
