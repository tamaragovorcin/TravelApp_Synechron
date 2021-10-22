package com.example.TravelApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyAmountDTO {
    private String currency;
    private Double amount;
    private Double value;
    private String formula;
    private String formulaDinProtivr;
}
