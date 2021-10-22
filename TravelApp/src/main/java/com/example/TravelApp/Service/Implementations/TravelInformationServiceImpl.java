package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.DTOs.TravelInfoSearchDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Mapper.TravelDetailsMapper;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Repository.TravelDetailsRepository;
import com.example.TravelApp.Service.Interfaces.ITravelInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class TravelInformationServiceImpl implements ITravelInformationService {

    @Autowired
    TravelDetailsRepository travelDetailsRepository;

    @Autowired
    TravelDetailsMapper travelDetailsMapper;


    @Autowired
    TravelDetailsServiceImpl travelDetailsService;

    @Override
    public Set<TravelDetailsDTO> findBasicTravelInformation(String departureDateString, String arrivalDateString) {

        List<TravelDetails> travelDetailsList = (departureDateString != null && arrivalDateString != null) ?
                getTravelInfoWithDates(departureDateString, arrivalDateString) : getTravelInfoNoDates();


        Set<TravelDetailsDTO> travelDetailsDTOS = new HashSet<>();
        if(departureDateString == null && arrivalDateString == null){
            travelDetailsDTOS = travelDetailsService.findAll();
        }
        else {

            for (TravelDetails travelDetails : travelDetailsList) {
                travelDetailsDTOS.add(travelDetailsMapper.travelDetailsToDTO(travelDetails));
            }
        }
        return travelDetailsService.updateTravelDetails(travelDetailsDTOS);
    }

    @Override
    public List<TravelDetails> getTravelInfoNoDates() {
        return travelDetailsRepository.findAll();
    }

    @Override
    public List<TravelDetails> getTravelInfoWithDates(String departureDateString, String arrivalDateString) {

        LocalDate departureDate = TimeAndDateParser.parseToLocalDate(departureDateString);
        LocalDate arrivalDate = TimeAndDateParser.parseToLocalDate(arrivalDateString);
        return travelDetailsRepository.findAllTravelDetailsBetweenDates(departureDate, arrivalDate);
    }

    @Override
    public Set<TravelDetailsDTO> findBasicTravelInformationBySearch(TravelInfoSearchDTO travelInfoSearchDTO) {

        List<TravelDetails> travelDetailsList = (travelInfoSearchDTO.getArrivalDateString() != null && travelInfoSearchDTO.getDepartureDateString() != null) ?
                getTravelInfoWithDates(travelInfoSearchDTO.getDepartureDateString(), travelInfoSearchDTO.getArrivalDateString()) : getTravelInfoNoDates();

        Set<TravelDetailsDTO> travelDetailsDTOS = new HashSet<>();
        for (TravelDetails travelDetails : travelDetailsList) {
            if (dataIsAppropriateForSearch(travelDetails,travelInfoSearchDTO)) {
                travelDetailsDTOS.add(travelDetailsMapper.travelDetailsToDTO(travelDetails));
            }
        }

        return travelDetailsDTOS;
    }


    private boolean dataIsAppropriateForSearch(TravelDetails travelDetails,TravelInfoSearchDTO travelInfoSearchDTO) {
        if(travelInfoSearchDTO.getDepartingCountry().equals("") ||
                travelDetails.getDepartingCountry().toLowerCase().startsWith(travelInfoSearchDTO.getDepartingCountry().toLowerCase())) {
            if(travelInfoSearchDTO.getArrivalCountry().equals("") ||
                    travelDetails.getArrivalCountry().toLowerCase().startsWith(travelInfoSearchDTO.getArrivalCountry().toLowerCase())) {
                if(travelInfoSearchDTO.getEmployeeName().equals("") ||
                        travelDetails.getEmployee().getFirstName().toLowerCase().startsWith(travelInfoSearchDTO.getEmployeeName().toLowerCase())) {
                    return travelInfoSearchDTO.getEmployeeLastName().equals("") ||
                            travelDetails.getEmployee().getLastName().toLowerCase().startsWith(travelInfoSearchDTO.getEmployeeLastName().toLowerCase());
                }
            }
        }
        return false;
    }




}
