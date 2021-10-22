package com.example.TravelApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelInfoSearchDTO {
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String departureDateString;
    private String arrivalDateString;
    private String employeeName;
    private String employeeLastName;
    private String departingCountry;
    private String arrivalCountry;

    @Override
    public String toString() {
        return "TravelInfoSearchDTO{" +
                "departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureDateString='" + departureDateString + '\'' +
                ", arrivalDateString='" + arrivalDateString + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", departingCountry='" + departingCountry + '\'' +
                ", arrivalCountry='" + arrivalCountry + '\'' +
                '}';
    }
}
