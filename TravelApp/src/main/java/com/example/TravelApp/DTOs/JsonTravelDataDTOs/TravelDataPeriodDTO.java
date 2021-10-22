package com.example.TravelApp.DTOs.JsonTravelDataDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelDataPeriodDTO {
    private String start_date;
    private String end_date;
}
