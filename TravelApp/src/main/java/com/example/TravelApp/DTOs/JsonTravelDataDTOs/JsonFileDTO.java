package com.example.TravelApp.DTOs.JsonTravelDataDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonFileDTO {
    public List<JsonEmployeeAccommodationDetail> employee_accommodation_details;
    public List<JsonEmployeeTravelDetail> employee_travel_details;
    public List<JsonEmployeeDetail> employee_details;
}
