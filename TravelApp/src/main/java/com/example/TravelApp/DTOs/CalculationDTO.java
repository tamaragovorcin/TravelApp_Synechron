package com.example.TravelApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculationDTO {
    private double middleCourse;
    private double numberDailyAllowancesAbroad;
    private double allowancesSum;
}
