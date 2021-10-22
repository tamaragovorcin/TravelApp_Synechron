package com.example.TravelApp.Mapper;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeAccommodationDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Service.Implementations.EmployeeServiceImpl;
import com.example.TravelApp.Service.Interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccommodationDetailsMapper {

    @Autowired
    IEmployeeService employeeService;


    @Autowired
    TravelDetailsMapper travelDetailsMapper;

    public AccommodationDetailsDTO accommodationDetailsToDTONoId(AccommodationDetails accommodationDetails) {
        AccommodationDetailsDTO accommodationDetailsDTO = new AccommodationDetailsDTO();

        accommodationDetailsDTO.setBillingDetails(accommodationDetails.getBillingDetails());

        Set<TravelDetailsDTO> travelDetailsDTOSet = new HashSet<>();
        for(TravelDetails travelDetails: accommodationDetails.getTravelDetails()){
            travelDetailsDTOSet.add(travelDetailsMapper.travelDetailsToDTO(travelDetails));
        }
        accommodationDetailsDTO.setTravelDetails(travelDetailsDTOSet);
        accommodationDetailsDTO.setAccountName(accommodationDetails.getAccountName());
        accommodationDetailsDTO.setBookingPurpose(accommodationDetails.getBookingPurpose());
        accommodationDetailsDTO.setAccommodationRequestNo(accommodationDetails.getAccommodationRequestNo());
        if(accommodationDetails.getCheckInDate() != null) {
            accommodationDetailsDTO.setCheckInDate(accommodationDetails.getCheckInDate().toString());

        }
        if(accommodationDetails.getCheckOutDate() != null) {

            accommodationDetailsDTO.setCheckOutDate(accommodationDetails.getCheckOutDate().toString());
        }
        accommodationDetailsDTO.setEmployeeId(accommodationDetails.getEmployee().getEmployeeId());
        accommodationDetailsDTO.setPreviousId(accommodationDetails.getPreviousId());
        accommodationDetailsDTO.setUpdateDate(accommodationDetails.getUpdateDate());
        accommodationDetailsDTO.setUpdateTime(accommodationDetails.getUpdateTime());

        return accommodationDetailsDTO;
    }

    public Set<AccommodationDetailsDTO> getAccommodationsDTOS(List<AccommodationDetails> accommodationDetails) {

        Set<AccommodationDetailsDTO> accommodationDetailsDTOS = new HashSet<>();
        for (AccommodationDetails accommodationDetails1 : accommodationDetails) {
            accommodationDetailsDTOS.add(accommodationDetailsToDTO(accommodationDetails1));
        }
        return accommodationDetailsDTOS;
    }

    public AccommodationDetailsDTO accommodationDetailsToDTO(AccommodationDetails accommodationDetails) {

        AccommodationDetailsDTO accommodationDetailsDTO = new AccommodationDetailsDTO();
        accommodationDetailsDTO.setId(accommodationDetails.getId());
        accommodationDetailsDTO.setBillingDetails(accommodationDetails.getBillingDetails());

       Set<TravelDetailsDTO> travelDetailsDTOSet = new HashSet<>();

           for (TravelDetails travelDetails : accommodationDetails.getTravelDetails()) {
               travelDetailsDTOSet.add(travelDetailsMapper.travelDetailsToDTOForAccommodation(travelDetails));
           }
          accommodationDetailsDTO.setTravelDetails(travelDetailsDTOSet);

        accommodationDetailsDTO.setAccountName(accommodationDetails.getAccountName());
        accommodationDetailsDTO.setBookingPurpose(accommodationDetails.getBookingPurpose());
        accommodationDetailsDTO.setAccommodationRequestNo(accommodationDetails.getAccommodationRequestNo());
        accommodationDetailsDTO.setEmployeeId(accommodationDetails.getEmployee().getEmployeeId());
        accommodationDetailsDTO.setPreviousId(accommodationDetails.getPreviousId());
        accommodationDetailsDTO.setUpdateDate(accommodationDetails.getUpdateDate());
        accommodationDetailsDTO.setUpdateTime(accommodationDetails.getUpdateTime());

        String hours = "";
        String minutes = "";
        String day = "";
        String month = "";

        if (accommodationDetails.getCheckInDate() != null) {
            if (accommodationDetails.getCheckInDate().getHour() < 10) {
                hours = "0" + accommodationDetails.getCheckInDate().getHour();
            } else {
                hours = "" + accommodationDetails.getCheckInDate().getHour();
            }


            if (accommodationDetails.getCheckInDate().getMinute() < 10) {
                minutes = "0" + accommodationDetails.getCheckInDate().getMinute();
            } else {
                minutes = "" + accommodationDetails.getCheckInDate().getMinute();
            }

            if (accommodationDetails.getCheckInDate().getDayOfMonth() < 10) {
                day = "0" + accommodationDetails.getCheckInDate().getDayOfMonth();
            } else {
                day = "" + accommodationDetails.getCheckInDate().getDayOfMonth();
            }

            if (accommodationDetails.getCheckInDate().getMonthValue() < 10) {
                month = "0" + accommodationDetails.getCheckInDate().getMonthValue();
            } else {
                month = "" + accommodationDetails.getCheckInDate().getMonthValue();
            }
            String checkInDateString = month + "/" + day + "/" + accommodationDetails.getCheckInDate().getYear() + " " + hours + ":" + minutes;
            accommodationDetailsDTO.setCheckInDate(checkInDateString);

    }
    if(accommodationDetails.getCheckOutDate() !=null) {
        if (accommodationDetails.getCheckOutDate().getHour() < 10) {
            hours = "0" + accommodationDetails.getCheckOutDate().getHour();
        } else {
            hours = "" + accommodationDetails.getCheckOutDate().getHour();
        }
        if (accommodationDetails.getCheckOutDate().getMinute() < 10) {
            minutes = "0" + accommodationDetails.getCheckOutDate().getMinute();
        } else {
            minutes = "" + accommodationDetails.getCheckOutDate().getMinute();
        }

        if (accommodationDetails.getCheckOutDate().getDayOfMonth() < 10) {
            day = "0" + accommodationDetails.getCheckOutDate().getDayOfMonth();
        } else {
            day = "" + accommodationDetails.getCheckOutDate().getDayOfMonth();
        }

        if (accommodationDetails.getCheckOutDate().getMonthValue() < 10) {
            month = "0" + accommodationDetails.getCheckOutDate().getMonthValue();
        } else {
            month = "" + accommodationDetails.getCheckOutDate().getMonthValue();
        }
        String getCheckOutDateString = month + "/" + day + "/" + accommodationDetails.getCheckOutDate().getYear() + " " + hours + ":" + minutes;
        accommodationDetailsDTO.setCheckOutDate(getCheckOutDateString);
    }

        return accommodationDetailsDTO;
    }

    public AccommodationDetailsDTO accommodationDetailsToDTOForTravel(AccommodationDetails accommodationDetails) {

        AccommodationDetailsDTO accommodationDetailsDTO = new AccommodationDetailsDTO();
        accommodationDetailsDTO.setId(accommodationDetails.getId());
        accommodationDetailsDTO.setBillingDetails(accommodationDetails.getBillingDetails());

        accommodationDetailsDTO.setAccountName(accommodationDetails.getAccountName());
        accommodationDetailsDTO.setBookingPurpose(accommodationDetails.getBookingPurpose());
        accommodationDetailsDTO.setAccommodationRequestNo(accommodationDetails.getAccommodationRequestNo());
        accommodationDetailsDTO.setEmployeeId(accommodationDetails.getEmployee().getEmployeeId());
        accommodationDetailsDTO.setPreviousId(accommodationDetails.getPreviousId());
        accommodationDetailsDTO.setUpdateDate(accommodationDetails.getUpdateDate());
        accommodationDetailsDTO.setUpdateTime(accommodationDetails.getUpdateTime());

        String hours = "";
        String minutes = "";
        String day = "";
        String month = "";

        if (accommodationDetails.getCheckInDate() != null) {
            if (accommodationDetails.getCheckInDate().getHour() < 10) {
                hours = "0" + accommodationDetails.getCheckInDate().getHour();
            } else {
                hours = "" + accommodationDetails.getCheckInDate().getHour();
            }


            if (accommodationDetails.getCheckInDate().getMinute() < 10) {
                minutes = "0" + accommodationDetails.getCheckInDate().getMinute();
            } else {
                minutes = "" + accommodationDetails.getCheckInDate().getMinute();
            }

            if (accommodationDetails.getCheckInDate().getDayOfMonth() < 10) {
                day = "0" + accommodationDetails.getCheckInDate().getDayOfMonth();
            } else {
                day = "" + accommodationDetails.getCheckInDate().getDayOfMonth();
            }

            if (accommodationDetails.getCheckInDate().getMonthValue() < 10) {
                month = "0" + accommodationDetails.getCheckInDate().getMonthValue();
            } else {
                month = "" + accommodationDetails.getCheckInDate().getMonthValue();
            }
            String checkInDateString = month + "/" + day + "/" + accommodationDetails.getCheckInDate().getYear() + " " + hours + ":" + minutes;
            accommodationDetailsDTO.setCheckInDate(checkInDateString);

        }
        if(accommodationDetails.getCheckOutDate() !=null) {
            if (accommodationDetails.getCheckOutDate().getHour() < 10) {
                hours = "0" + accommodationDetails.getCheckOutDate().getHour();
            } else {
                hours = "" + accommodationDetails.getCheckOutDate().getHour();
            }
            if (accommodationDetails.getCheckOutDate().getMinute() < 10) {
                minutes = "0" + accommodationDetails.getCheckOutDate().getMinute();
            } else {
                minutes = "" + accommodationDetails.getCheckOutDate().getMinute();
            }

            if (accommodationDetails.getCheckOutDate().getDayOfMonth() < 10) {
                day = "0" + accommodationDetails.getCheckOutDate().getDayOfMonth();
            } else {
                day = "" + accommodationDetails.getCheckOutDate().getDayOfMonth();
            }

            if (accommodationDetails.getCheckOutDate().getMonthValue() < 10) {
                month = "0" + accommodationDetails.getCheckOutDate().getMonthValue();
            } else {
                month = "" + accommodationDetails.getCheckOutDate().getMonthValue();
            }
            String getCheckOutDateString = month + "/" + day + "/" + accommodationDetails.getCheckOutDate().getYear() + " " + hours + ":" + minutes;
            accommodationDetailsDTO.setCheckOutDate(getCheckOutDateString);
        }

        return accommodationDetailsDTO;
    }



    public AccommodationDetails accommodationDetailsDTOtoAccommodationDetails(AccommodationDetailsDTO accommodationDetailsDTO) {
        AccommodationDetails accommodationDetails = new AccommodationDetails();

        accommodationDetails.setId(accommodationDetailsDTO.getId());
        accommodationDetails.setBillingDetails(accommodationDetailsDTO.getBillingDetails());
       Set<TravelDetails> travelDetailsSet = new HashSet<>();
        for(TravelDetailsDTO travelDetails: accommodationDetailsDTO.getTravelDetails()){
            travelDetailsSet.add(travelDetailsMapper.travelDetailsDTOToTravelDetails(travelDetails));
        }
        accommodationDetails.setTravelDetails(travelDetailsSet);
        accommodationDetails.setAccountName(accommodationDetailsDTO.getAccountName());
        accommodationDetails.setBookingPurpose(accommodationDetailsDTO.getBookingPurpose());
        accommodationDetails.setAccommodationRequestNo(accommodationDetailsDTO.getAccommodationRequestNo());
        accommodationDetails.setEmployee(employeeService.findByEmployeeId(accommodationDetailsDTO.getEmployeeId()));
        accommodationDetails.setPreviousId(accommodationDetailsDTO.getPreviousId());
        accommodationDetails.setUpdateDate(accommodationDetailsDTO.getUpdateDate());
        accommodationDetails.setUpdateTime(accommodationDetailsDTO.getUpdateTime());

        if(accommodationDetailsDTO.getCheckInDate() != null && !accommodationDetailsDTO.getCheckInDate().contains("T")  && !accommodationDetailsDTO.getCheckInDate().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            LocalDateTime checkInDate = LocalDateTime.parse(accommodationDetailsDTO.getCheckInDate(), formatter);

            accommodationDetails.setCheckInDate(checkInDate);



        }
        else if(accommodationDetailsDTO.getCheckInDate() != null &&  !accommodationDetailsDTO.getCheckInDate().equals("")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime checkInDate = LocalDateTime.parse(accommodationDetailsDTO.getCheckInDate(), formatter);

            accommodationDetails.setCheckInDate(checkInDate);


        }

        if(accommodationDetailsDTO.getCheckOutDate() != null && !accommodationDetailsDTO.getCheckOutDate().contains("T")  && !accommodationDetailsDTO.getCheckOutDate().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

            LocalDateTime checkOutDate = LocalDateTime.parse(accommodationDetailsDTO.getCheckOutDate(), formatter);

            accommodationDetails.setCheckOutDate(checkOutDate);

        }
        else if(accommodationDetailsDTO.getCheckOutDate() != null &&  !accommodationDetailsDTO.getCheckOutDate().equals("")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            LocalDateTime checkOutDate = LocalDateTime.parse(accommodationDetailsDTO.getCheckOutDate(), formatter);

            accommodationDetails.setCheckOutDate(checkOutDate);
        }

        return accommodationDetails;
    }

    public AccommodationDetails accommodationDetailsDTOtoAccommodationDetailsForTravel(AccommodationDetailsDTO accommodationDetailsDTO) {
        AccommodationDetails accommodationDetails = new AccommodationDetails();

        accommodationDetails.setId(accommodationDetailsDTO.getId());
        accommodationDetails.setBillingDetails(accommodationDetailsDTO.getBillingDetails());

        accommodationDetails.setAccountName(accommodationDetailsDTO.getAccountName());
        accommodationDetails.setBookingPurpose(accommodationDetailsDTO.getBookingPurpose());
        accommodationDetails.setAccommodationRequestNo(accommodationDetailsDTO.getAccommodationRequestNo());
        accommodationDetails.setEmployee(employeeService.findByEmployeeId(accommodationDetailsDTO.getEmployeeId()));
        accommodationDetails.setPreviousId(accommodationDetailsDTO.getPreviousId());
        accommodationDetails.setUpdateDate(accommodationDetailsDTO.getUpdateDate());
        accommodationDetails.setUpdateTime(accommodationDetailsDTO.getUpdateTime());

        if(accommodationDetailsDTO.getCheckInDate() != null && !accommodationDetailsDTO.getCheckInDate().contains("T")  && !accommodationDetailsDTO.getCheckInDate().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            LocalDateTime checkInDate = LocalDateTime.parse(accommodationDetailsDTO.getCheckInDate(), formatter);

            accommodationDetails.setCheckInDate(checkInDate);



        }
        else if(accommodationDetailsDTO.getCheckInDate() != null &&  !accommodationDetailsDTO.getCheckInDate().equals("")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime checkInDate = LocalDateTime.parse(accommodationDetailsDTO.getCheckInDate(), formatter);

            accommodationDetails.setCheckInDate(checkInDate);


        }

        if(accommodationDetailsDTO.getCheckOutDate() != null && !accommodationDetailsDTO.getCheckOutDate().contains("T")  && !accommodationDetailsDTO.getCheckOutDate().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

            LocalDateTime checkOutDate = LocalDateTime.parse(accommodationDetailsDTO.getCheckOutDate(), formatter);

            accommodationDetails.setCheckOutDate(checkOutDate);

        }
        else if(accommodationDetailsDTO.getCheckOutDate() != null &&  !accommodationDetailsDTO.getCheckOutDate().equals("")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            LocalDateTime checkOutDate = LocalDateTime.parse(accommodationDetailsDTO.getCheckOutDate(), formatter);

            accommodationDetails.setCheckOutDate(checkOutDate);
        }

        return accommodationDetails;
    }

    public AccommodationDetails jsonDetailsToAccommodationDetails(JsonEmployeeAccommodationDetail jsonEmployeeAccommodationDetail) {

        AccommodationDetails accommodationDetails = new AccommodationDetails();

        accommodationDetails.setAccountName(jsonEmployeeAccommodationDetail.getAccountName());
        accommodationDetails.setBookingPurpose(jsonEmployeeAccommodationDetail.getBookingPurpose());
        accommodationDetails.setAccommodationRequestNo(jsonEmployeeAccommodationDetail.getAccommodationRequestNo());
        accommodationDetails.setCheckInDate(TimeAndDateParser.parseDateAndTime(jsonEmployeeAccommodationDetail.getCheckInDate()));
        accommodationDetails.setCheckOutDate(TimeAndDateParser.parseDateAndTime(jsonEmployeeAccommodationDetail.getCheckOutDate()));
        accommodationDetails.setEmployee(employeeService.findByEmployeeId(jsonEmployeeAccommodationDetail.getEmployeeID()));

        return accommodationDetails;
    }
}
