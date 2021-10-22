package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeAccommodationDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Mapper.AccommodationDetailsMapper;
import com.example.TravelApp.Mapper.EmployeeMapper;
import com.example.TravelApp.Mapper.TravelDetailsMapper;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Repository.AccomodationDetailsRepository;
import com.example.TravelApp.Service.Interfaces.IAccommodationDetailsService;
import com.example.TravelApp.Service.Interfaces.IEmployeeService;
import com.example.TravelApp.Service.Interfaces.ITravelDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccommodationDetailsServiceImpl implements IAccommodationDetailsService {

    @Autowired
    AccomodationDetailsRepository accomodationDetailsRepository;

    @Autowired
    AccommodationDetailsMapper accommodationDetailsMapper;


    @Autowired
    ITravelDetailsService travelDetailsService;


    @Autowired
    TravelDetailsServiceImpl travelService;

    @Autowired
    TravelDetailsMapper travelDetailsMapper;


    @Autowired
    IEmployeeService employeeService;


    @Autowired
    EmployeeMapper employeeMapper;


    @Autowired
    TimeAndDateParser timeAndDateParser;

    @Override
    public Set<AccommodationDetailsDTO> findAll() {
        List<AccommodationDetails> accommodationDetailsList =  accomodationDetailsRepository.findAll();
        return accommodationDetailsMapper.getAccommodationsDTOS(accommodationDetailsList);
    }



    @Override
    public AccommodationDetailsDTO save(AccommodationDetailsDTO accommodationDetailsDTO) {
        AccommodationDetails accommodationDetails =
                accomodationDetailsRepository.save(accommodationDetailsMapper.accommodationDetailsDTOtoAccommodationDetails(accommodationDetailsDTO));
        AccommodationDetailsDTO detailsDTO = accommodationDetailsMapper.accommodationDetailsToDTO(accommodationDetails);
        return detailsDTO;
    }

    @Override
    public  Set<TravelDetailsDTO> update(AccommodationDetailsDTO accommodationDetailsDTO) {


        AccommodationDetails accommodationDetails = accomodationDetailsRepository.findById(accommodationDetailsDTO.getId()).orElse(null);
        AccommodationDetails accommodationDetailsUpdated = accommodationDetailsMapper.accommodationDetailsDTOtoAccommodationDetails(accommodationDetailsDTO);
        accommodationDetailsUpdated.setUpdateDate(currentDate());
        accommodationDetailsUpdated.setUpdateTime(currentTime());
        accommodationDetailsUpdated.setTravelDetails(accommodationDetails.getTravelDetails());
        if(accommodationDetails != null){
            accommodationDetailsUpdated.setPreviousId(String.valueOf(accommodationDetails.getId()));
        }

        for(TravelDetails travelDetails1:  accommodationDetails.getTravelDetails()){
            TravelDetailsDTO travelDetailsDTO = travelDetailsService.findById(travelDetails1.getId());
            travelDetailsDTO.setAccommodationDetails(accommodationDetailsMapper.accommodationDetailsToDTONoId(accommodationDetailsUpdated));
            travelDetailsService.save(travelDetailsDTO);
        }

        return travelService.updateTravelDetails(travelDetailsService.findAll());


    }
    private String currentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);
        return date;
    }

    private String currentTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String time = currentTime.format(formatter);
        return time;
    }


    @Override
    public Set<AccommodationDetails> findByAccommodationRequestNo(String id) {
        Set<AccommodationDetails> accommodationDetails = this.accomodationDetailsRepository.findByAccommodationRequestNo(id);

        return accommodationDetails;
    }

    @Override
    public Set<AccommodationDetailsDTO> parseAccommodationDetails(List<JsonEmployeeAccommodationDetail> employee_accommodation_details) {
        List<AccommodationDetails> accommodationDetailsList = new ArrayList<>();

        for (JsonEmployeeAccommodationDetail jsonEmployeeAccommodationDetail: employee_accommodation_details) {
            if(findByAccommodationRequestNo(String.valueOf(jsonEmployeeAccommodationDetail.getAccommodationRequestNo())).isEmpty()) {
                AccommodationDetails accommodationDetails = accommodationDetailsMapper.jsonDetailsToAccommodationDetails(jsonEmployeeAccommodationDetail);
                accommodationDetailsList.add(accomodationDetailsRepository.save(accommodationDetails));
            }
        }
        return accommodationDetailsMapper.getAccommodationsDTOS(accommodationDetailsList);
    }

    @Override
    public String accommodationAndTravelCompatibility(AccommodationDetailsDTO accommodationDetailsDTO, TravelDetailsDTO travelDetailsDTO) {
        if(accommodationDetailsDTO.getEmployeeId().equals(travelDetailsDTO.getEmployee().getEmployeeId())){
            if(accommodationDetailsDTO.getCheckInDate()!=null && travelDetailsDTO.getArrivalDate()!=null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
                LocalDateTime checkInDateDT = LocalDateTime.parse(accommodationDetailsDTO.getCheckInDate(), formatter);
                LocalDateTime arrivalDateDT = LocalDateTime.parse(travelDetailsDTO.getArrivalDate().concat(" 00:00"), formatter);
                LocalDate checkInDate = checkInDateDT.toLocalDate();
                LocalDate arrivalDate = arrivalDateDT.toLocalDate();
                Period period = Period.between(checkInDate, arrivalDate);
                if (period.getYears() == 0 && period.getMonths() == 0 && period.getDays() <= 2 && period.getDays() >= -2) {
                    return accommodationDetailsDTO.getAccommodationRequestNo();
                }
            }
        }
        return null;
    }

    @Override
    public AccommodationDetailsDTO findById(long id) {
        AccommodationDetails accommodationDetails = accomodationDetailsRepository.findById(id).orElse(null);
        AccommodationDetailsDTO accommodationDetailsDTO = accommodationDetailsMapper.accommodationDetailsToDTO(accommodationDetails);
        return accommodationDetailsDTO;
    }

    @Override
    public AccommodationDetails findByIdNoDTO(long id) {
        return accomodationDetailsRepository.findById(id).get();
    }

}