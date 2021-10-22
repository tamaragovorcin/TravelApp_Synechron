package com.example.TravelApp.Unit.Constants;


import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeAccommodationDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Model.TravelDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class AccommodationConstants {

    public static final long ID = 1;
    public static final long ID2 = 2;
    public static final String ACCOUNT_REQUEST_NUMBER = "T25678";
    public static final String ACCOUNT_REQUEST_NUMBER2 = "P25679";
    public static final String ACCOUNT_NAME = "Account name";
    public static final Employee EMPLOYEE = EmployeeConstants.EMPLOYEE_UPDATE;
    public static final EmployeeDTO EMPLOYEE_DTO = EmployeeConstants.EMPLOYEE_DTO;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    public static final LocalDateTime CHECK_IN_DATE =  LocalDateTime.parse("01/01/2020 00:00", formatter);
    public static final LocalDateTime CHECK_OUT_DATE =  LocalDateTime.parse("01/01/2020 00:00", formatter);
    public static final String BOOKING_PURPOSE = "Booking purpose";
    public static final String NEW_BOOKING_PURPOSE = "Booking purpose that is new";
    public static final String BILLING_DETAILS = "Billing details";
    public static final String NEW_BILLING_DETAILS = "Billing details that is new";
    public static final String PREVIOUS_ID = "0";
    public static final String UPDATE_DATE = null;
    public static final String UPDATE_TIME = null;
    public static final Set<TravelDetails> TRAVEL_DETAILS = new HashSet<>();
    public static final Set<TravelDetailsDTO> TRAVEL_DETAILS_DTO = new HashSet<>();

    public static final AccommodationDetailsDTO ACCOMMODATION_DETAILSDTO = new AccommodationDetailsDTO( ID, ACCOUNT_NAME,"1",ACCOUNT_REQUEST_NUMBER, BOOKING_PURPOSE,BILLING_DETAILS,null, PREVIOUS_ID,  UPDATE_DATE, UPDATE_TIME, CHECK_OUT_DATE.toString(), CHECK_IN_DATE.toString());
    public static final AccommodationDetails ACCOMMODATION_DETAILS = new AccommodationDetails(ID, ACCOUNT_NAME,EMPLOYEE,CHECK_OUT_DATE, CHECK_IN_DATE,ACCOUNT_REQUEST_NUMBER, BOOKING_PURPOSE, BILLING_DETAILS,PREVIOUS_ID, UPDATE_DATE, UPDATE_TIME, TRAVEL_DETAILS);
    public static final AccommodationDetails ACCOMMODATION_DETAILS2 = new AccommodationDetails(ID2, ACCOUNT_NAME,EMPLOYEE,CHECK_OUT_DATE, CHECK_IN_DATE,ACCOUNT_REQUEST_NUMBER2, BOOKING_PURPOSE, BILLING_DETAILS,PREVIOUS_ID, UPDATE_DATE, UPDATE_TIME, TRAVEL_DETAILS);
    public static final AccommodationDetailsDTO ACCOMMODATION_DETAILS_DTO = new AccommodationDetailsDTO( ID, ACCOUNT_NAME,"1",ACCOUNT_REQUEST_NUMBER, BOOKING_PURPOSE,BILLING_DETAILS,getTravelDetailsDTO(), PREVIOUS_ID,  UPDATE_DATE, UPDATE_TIME, CHECK_OUT_DATE.toString(), CHECK_IN_DATE.toString());
    public static final AccommodationDetailsDTO ACCOMMODATION_DETAILS_DTO2 = new AccommodationDetailsDTO( ID2, ACCOUNT_NAME,"1",ACCOUNT_REQUEST_NUMBER2, BOOKING_PURPOSE,BILLING_DETAILS,TRAVEL_DETAILS_DTO, PREVIOUS_ID,  UPDATE_DATE, UPDATE_TIME, CHECK_OUT_DATE.toString(), CHECK_IN_DATE.toString());
    public static final AccommodationDetailsDTO ACCOMMODATION_DETAILS_DTO3 = new AccommodationDetailsDTO( ID, ACCOUNT_NAME,"1",ACCOUNT_REQUEST_NUMBER, BOOKING_PURPOSE,BILLING_DETAILS,getTravelDetailsDTO(), PREVIOUS_ID,  UPDATE_DATE, UPDATE_TIME, CHECK_OUT_DATE.toString(), "01/01/2021 00:00");
    public static final AccommodationDetailsDTO ACCOMMODATION_DETAILS_DTO4 = new AccommodationDetailsDTO( ID, ACCOUNT_NAME,"1",ACCOUNT_REQUEST_NUMBER, BOOKING_PURPOSE,BILLING_DETAILS,getTravelDetailsDTO(), PREVIOUS_ID,  UPDATE_DATE, UPDATE_TIME, CHECK_OUT_DATE.toString(), "01/01/2020 00:00");
    public static final JsonEmployeeAccommodationDetail JSON_EMPLOYEE_ACCOMMODATION_DETAIL = new JsonEmployeeAccommodationDetail(ACCOUNT_NAME, EmployeeConstants.EMPLOYEE_ID, CHECK_OUT_DATE.toString(), CHECK_IN_DATE.toString(), ACCOUNT_REQUEST_NUMBER, BOOKING_PURPOSE);

    public static final Set<AccommodationDetails> getAccommodationDetailsSet() {

        Set<AccommodationDetails> accommodationDetailsSet = new HashSet<>();

        accommodationDetailsSet.add(ACCOMMODATION_DETAILS);

        return accommodationDetailsSet;
    }

    public static final Set<TravelDetailsDTO> getTravelDetailsDTO() {

        Set<TravelDetailsDTO> travelDetailsDTOS = new HashSet<>();

        travelDetailsDTOS.add(TravelDetailsConstants.getTravelDetailsDTO());

        return travelDetailsDTOS;
    }

    public static final Set<AccommodationDetails> getAccommodationDetailsSet2() {

        Set<AccommodationDetails> accommodationDetailsSet = new HashSet<>();

        accommodationDetailsSet.add(ACCOMMODATION_DETAILS2);

        return accommodationDetailsSet;
    }

    public static final Set<AccommodationDetailsDTO> getAccommodationDetailsDTOSet() {

        Set<AccommodationDetailsDTO> accommodationDetailsSet = new HashSet<>();


        accommodationDetailsSet.add(ACCOMMODATION_DETAILS_DTO2);
        accommodationDetailsSet.add(ACCOMMODATION_DETAILS_DTO);

        return accommodationDetailsSet;
    }

    public static final AccommodationDetailsDTO getAccommodationDetailsDTO() {

        return ACCOMMODATION_DETAILS_DTO3;
    }

    public static final AccommodationDetailsDTO getAccommodationDetailsDTO2() {

        return ACCOMMODATION_DETAILS_DTO4;
    }

    public static final TravelDetailsDTO getTravelDetailsDTOForCompatibility() {

        return TravelDetailsConstants.getTravelDetailsDTOForAccommodation();
    }

}
