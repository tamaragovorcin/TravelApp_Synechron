package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.DTOs.TravelInfoSearchDTO;
import com.example.TravelApp.Model.TravelDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ITravelInformationService {

    Set<TravelDetailsDTO> findBasicTravelInformation(String departureDate, String arrivalDate);
    List<TravelDetails> getTravelInfoNoDates();
    List<TravelDetails> getTravelInfoWithDates(String departureDateString, String arrivalDateString);
    Set<TravelDetailsDTO> findBasicTravelInformationBySearch(TravelInfoSearchDTO travelInfoSearchDTO);

}
