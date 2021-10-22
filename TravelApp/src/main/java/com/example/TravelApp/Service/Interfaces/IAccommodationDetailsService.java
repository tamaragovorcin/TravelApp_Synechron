package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeAccommodationDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.TravelDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IAccommodationDetailsService {

    Set<AccommodationDetailsDTO> findAll();
    AccommodationDetailsDTO save(AccommodationDetailsDTO accommodationDetailsDTO);
    Set<TravelDetailsDTO> update(AccommodationDetailsDTO accommodationDetailsDTO);
    Set<AccommodationDetails> findByAccommodationRequestNo(String id);

    AccommodationDetailsDTO findById(long id);

    AccommodationDetails findByIdNoDTO(long id);
    Set<AccommodationDetailsDTO> parseAccommodationDetails(List<JsonEmployeeAccommodationDetail> employee_accommodation_details);
    String accommodationAndTravelCompatibility(AccommodationDetailsDTO accommodationDetailsDTO, TravelDetailsDTO travelDetailsDTO);
}

