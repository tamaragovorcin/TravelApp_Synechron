package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeTravelDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Model.TravelDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ITravelDetailsService {

    Set<TravelDetailsDTO> findAll();
    TravelDetailsDTO save(TravelDetailsDTO travelDetailsDTO);
    Set<TravelDetailsDTO> update(TravelDetailsDTO travelDetailsDTO);
    void delete(long id);
    Set<TravelDetails> findByTravelReqID(String id);
    void isCircularTravel(String travelReqId);
    Set<TravelDetailsDTO> parseTravelDetails(List<JsonEmployeeTravelDetail> employee_travel_details);
    TravelDetailsDTO findById(long id);
    void addAccommodationDetailsToTravelDetails(Long travelReqID, Long accommodationRequestNo);
}
