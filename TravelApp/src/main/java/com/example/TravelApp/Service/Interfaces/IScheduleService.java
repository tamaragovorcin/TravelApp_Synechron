package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.JsonTravelDataDTOs.TokenDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONObject;

public interface IScheduleService {
    void reserveTransfer();
    void advancePayment();
    void payoff();
}
