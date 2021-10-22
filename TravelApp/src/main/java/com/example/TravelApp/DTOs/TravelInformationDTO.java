package com.example.TravelApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelInformationDTO {
    private EmployeeDTO employeeDTO;
    private AccommodationDetailsDTO accomodationDetailsDTO;
    private TravelDetailsDTO travelDetailsDTO;
}
