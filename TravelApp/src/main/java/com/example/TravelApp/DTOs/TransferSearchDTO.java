package com.example.TravelApp.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferSearchDTO {
    private String departureDateString;
    private String arrivalDateString;
    private String name;
    private String surname;
    private String departingCountry;
    private String arrivalCountry;
    private String departingFrom;
    private String arriveTo;
    private String reservationType;
}
