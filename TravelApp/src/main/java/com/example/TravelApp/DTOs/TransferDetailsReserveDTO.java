package com.example.TravelApp.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDetailsReserveDTO {

    private long id;
    private String currentDate;
    private String currentTime;
    private String transferDate;
    private String transferTime;
    private String transferCity;
    private String transferAddress;
    private String additionalComments;
}
