package com.example.TravelApp.DTOs.JsonTravelDataDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonEmployeeAccommodationDetail {

    public String accountName;
    public String employeeID;
    public String checkOutDate;
    public String checkInDate;
    public String accommodationRequestNo;
    public String bookingPurpose;
}
