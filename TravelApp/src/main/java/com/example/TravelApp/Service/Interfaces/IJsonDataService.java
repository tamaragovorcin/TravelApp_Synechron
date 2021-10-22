package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.JsonTravelDataDTOs.TokenDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONObject;

public interface IJsonDataService {
    void getTravelData() throws JsonProcessingException;
    TokenDTO getToken();
    JSONObject getTravelDataAsJson(TokenDTO token);
    void parseJsonFile(JSONObject jsonObject) throws JsonProcessingException;
}
