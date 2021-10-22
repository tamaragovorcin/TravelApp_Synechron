package com.example.TravelApp.Mapper;

import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeTravelDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Service.Interfaces.IEmployeeService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TravelDetailsMapper {

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    AccommodationDetailsMapper accommodationDetailsMapper;

    public TravelDetailsDTO travelDetailsToDTO(TravelDetails travelDetails) {
        TravelDetailsDTO travelDetailsDTO = new TravelDetailsDTO();

        travelDetailsDTO.setId(travelDetails.getId());
        travelDetailsDTO.setAccountName(travelDetails.getAccountName());
        travelDetailsDTO.setAdditionalComments(travelDetails.getAdditionalComments());
        travelDetailsDTO.setApprovedBy(travelDetails.getApprovedBy());
        travelDetailsDTO.setTravelReqID(travelDetails.getTravelReqID());
        travelDetailsDTO.setArrivalCountry(travelDetails.getArrivalCountry());

        if(travelDetails.getArrivalTime() == null || travelDetails.getArrivalTime().equals("")) {
            travelDetailsDTO.setArrivalTime("00:00");
        }
        else{
            travelDetailsDTO.setArrivalTime(travelDetails.getArrivalTime().toString());
        }

        travelDetailsDTO.setArriveTo(travelDetails.getArriveTo());
        travelDetailsDTO.setBookingPurpose(travelDetails.getBookingPurpose());
        travelDetailsDTO.setDepartFrom(travelDetails.getDepartFrom());
        travelDetailsDTO.setDepartingCountry(travelDetails.getDepartingCountry());

        if(travelDetails.getDepartureTime() == null || travelDetails.getDepartureTime().equals("")) {
            travelDetailsDTO.setDepartureTime("00:00");

        }
        else{
            travelDetailsDTO.setDepartureTime(travelDetails.getDepartureTime().toString());
        }

        travelDetailsDTO.setEmployee(employeeMapper.employeeToDTO(travelDetails.getEmployee()));

        if( travelDetails.getPartOfTheDayFrom() == null || travelDetails.getPartOfTheDayFrom().equals("")) {
            travelDetailsDTO.setPartOfTheDayFrom("00:00");

        }
        else{
            travelDetailsDTO.setPartOfTheDayFrom(travelDetails.getPartOfTheDayFrom().toString());
        }

        if(travelDetails.getPartOfTheDayTo() == null || travelDetails.getPartOfTheDayTo().equals("")) {
            travelDetailsDTO.setPartOfTheDayTo("00:00");
        }
        else{
            travelDetailsDTO.setPartOfTheDayTo(travelDetails.getPartOfTheDayTo().toString());

        }

        travelDetailsDTO.setTripPaidBy(travelDetails.getTripPaidBy());
        travelDetailsDTO.setVisaDetail(travelDetails.getVisaDetail());
        travelDetailsDTO.setPreviousId(travelDetails.getPreviousId());
        travelDetailsDTO.setUpdateDate(travelDetails.getUpdateDate());
        travelDetailsDTO.setUpdateTime(travelDetails.getUpdateTime());
        travelDetailsDTO.setMailSent(travelDetails.getMailSent());


        String day = "";
        String month = "";

        if (travelDetails.getArrivalDate() != null) {


            if (travelDetails.getArrivalDate().getDayOfMonth() < 10) {
                day = "0" + travelDetails.getArrivalDate().getDayOfMonth();
            } else {
                day = "" + travelDetails.getArrivalDate().getDayOfMonth();
            }

            if (travelDetails.getArrivalDate().getMonthValue() < 10) {
                month = "0" + travelDetails.getArrivalDate().getMonthValue();
            } else {
                month = "" + travelDetails.getArrivalDate().getMonthValue();
            }
            String arrivalDateString = month + "/" + day + "/" + travelDetails.getArrivalDate().getYear();
            travelDetailsDTO.setArrivalDate(arrivalDateString);

        }
        if(travelDetails.getDepartureDate() !=null) {

            if (travelDetails.getDepartureDate().getDayOfMonth() < 10) {
                day = "0" + travelDetails.getDepartureDate().getDayOfMonth();
            } else {
                day = "" + travelDetails.getDepartureDate().getDayOfMonth();
            }

            if (travelDetails.getDepartureDate().getMonthValue() < 10) {
                month = "0" + travelDetails.getDepartureDate().getMonthValue();
            } else {
                month = "" + travelDetails.getDepartureDate().getMonthValue();
            }
            String departureDateString = month + "/" + day + "/" + travelDetails.getDepartureDate().getYear();
            travelDetailsDTO.setDepartureDate(departureDateString);
        }


        if(travelDetails.getReturnTrip()!=null) {
            travelDetailsDTO.setReturnTrip("DA");
        } else {
            travelDetailsDTO.setReturnTrip("NE");
        }
        if(travelDetails.getAccommodationDetails()!=null) {
            travelDetailsDTO.setAccommodationDetails(accommodationDetailsMapper.accommodationDetailsToDTOForTravel(travelDetails.getAccommodationDetails()));
        }

        return travelDetailsDTO;
    }

    public TravelDetailsDTO travelDetailsToDTOForAccommodation(TravelDetails travelDetails) {
        TravelDetailsDTO travelDetailsDTO = new TravelDetailsDTO();

        travelDetailsDTO.setId(travelDetails.getId());
        travelDetailsDTO.setAccountName(travelDetails.getAccountName());
        travelDetailsDTO.setAdditionalComments(travelDetails.getAdditionalComments());
        travelDetailsDTO.setApprovedBy(travelDetails.getApprovedBy());
        travelDetailsDTO.setTravelReqID(travelDetails.getTravelReqID());
        travelDetailsDTO.setArrivalCountry(travelDetails.getArrivalCountry());

        if(travelDetails.getArrivalTime() == null || travelDetails.getArrivalTime().equals("")) {
            travelDetailsDTO.setArrivalTime("00:00");
        }
        else{
            travelDetailsDTO.setArrivalTime(travelDetails.getArrivalTime().toString());
        }

        travelDetailsDTO.setArriveTo(travelDetails.getArriveTo());
        travelDetailsDTO.setBookingPurpose(travelDetails.getBookingPurpose());
        travelDetailsDTO.setDepartFrom(travelDetails.getDepartFrom());
        travelDetailsDTO.setDepartingCountry(travelDetails.getDepartingCountry());

        if(travelDetails.getDepartureTime() == null || travelDetails.getDepartureTime().equals("")) {
            travelDetailsDTO.setDepartureTime("00:00");

        }
        else{
            travelDetailsDTO.setDepartureTime(travelDetails.getDepartureTime().toString());
        }

        travelDetailsDTO.setEmployee(employeeMapper.employeeToDTO(travelDetails.getEmployee()));

        if( travelDetails.getPartOfTheDayFrom() == null || travelDetails.getPartOfTheDayFrom().equals("")) {
            travelDetailsDTO.setPartOfTheDayFrom("00:00");

        }
        else{
            travelDetailsDTO.setPartOfTheDayFrom(travelDetails.getPartOfTheDayFrom().toString());
        }

        if(travelDetails.getPartOfTheDayTo() == null || travelDetails.getPartOfTheDayTo().equals("")) {
            travelDetailsDTO.setPartOfTheDayTo("00:00");
        }
        else{
            travelDetailsDTO.setPartOfTheDayTo(travelDetails.getPartOfTheDayTo().toString());

        }

        travelDetailsDTO.setTripPaidBy(travelDetails.getTripPaidBy());
        travelDetailsDTO.setVisaDetail(travelDetails.getVisaDetail());
        travelDetailsDTO.setPreviousId(travelDetails.getPreviousId());
        travelDetailsDTO.setUpdateDate(travelDetails.getUpdateDate());
        travelDetailsDTO.setUpdateTime(travelDetails.getUpdateTime());
        travelDetailsDTO.setMailSent(travelDetails.getMailSent());


        String day = "";
        String month = "";

        if (travelDetails.getArrivalDate() != null) {


            if (travelDetails.getArrivalDate().getDayOfMonth() < 10) {
                day = "0" + travelDetails.getArrivalDate().getDayOfMonth();
            } else {
                day = "" + travelDetails.getArrivalDate().getDayOfMonth();
            }

            if (travelDetails.getArrivalDate().getMonthValue() < 10) {
                month = "0" + travelDetails.getArrivalDate().getMonthValue();
            } else {
                month = "" + travelDetails.getArrivalDate().getMonthValue();
            }
            String arrivalDateString = month + "/" + day + "/" + travelDetails.getArrivalDate().getYear();
            travelDetailsDTO.setArrivalDate(arrivalDateString);

        }
        if(travelDetails.getDepartureDate() !=null) {

            if (travelDetails.getDepartureDate().getDayOfMonth() < 10) {
                day = "0" + travelDetails.getDepartureDate().getDayOfMonth();
            } else {
                day = "" + travelDetails.getDepartureDate().getDayOfMonth();
            }

            if (travelDetails.getDepartureDate().getMonthValue() < 10) {
                month = "0" + travelDetails.getDepartureDate().getMonthValue();
            } else {
                month = "" + travelDetails.getDepartureDate().getMonthValue();
            }
            String departureDateString = month + "/" + day + "/" + travelDetails.getDepartureDate().getYear();
            travelDetailsDTO.setDepartureDate(departureDateString);
        }


        if(travelDetails.getReturnTrip()!=null) {
            travelDetailsDTO.setReturnTrip("DA");
        } else {
            travelDetailsDTO.setReturnTrip("NE");
        }
        if(travelDetails.getAccommodationDetails()!=null) {
            travelDetailsDTO.setAccommodationDetails(accommodationDetailsMapper.accommodationDetailsToDTOForTravel(travelDetails.getAccommodationDetails()));
        }

        return travelDetailsDTO;
    }

    public TravelDetails travelDetailsDTOToTravelDetails(TravelDetailsDTO travelDetailsDTO) {
        TravelDetails travelDetails = new TravelDetails();

        if(travelDetailsDTO.getId() != 0) {
            travelDetails.setId(travelDetailsDTO.getId());
        }
        travelDetails.setAccountName(travelDetailsDTO.getAccountName());
        travelDetails.setAdditionalComments(travelDetailsDTO.getAdditionalComments());
        travelDetails.setApprovedBy(travelDetailsDTO.getApprovedBy());
        travelDetails.setTravelReqID(travelDetailsDTO.getTravelReqID());
        travelDetails.setArrivalCountry(travelDetailsDTO.getArrivalCountry());
        travelDetails.setArriveTo(travelDetailsDTO.getArriveTo());
        travelDetails.setBookingPurpose(travelDetailsDTO.getBookingPurpose());
        travelDetails.setDepartFrom(travelDetailsDTO.getDepartFrom());
        travelDetails.setDepartingCountry(travelDetailsDTO.getDepartingCountry());
        travelDetails.setEmployee(employeeMapper.employeeDTOtoEmployee(travelDetailsDTO.getEmployee()));
        travelDetails.setTripPaidBy(travelDetailsDTO.getTripPaidBy());
        travelDetails.setVisaDetail(travelDetailsDTO.getVisaDetail());
        travelDetails.setPreviousId(travelDetailsDTO.getPreviousId());
        travelDetails.setUpdateDate(travelDetailsDTO.getUpdateDate());
        travelDetails.setUpdateTime(travelDetailsDTO.getUpdateTime());
        travelDetails.setMailSent(travelDetailsDTO.getMailSent());


        travelDetails.setArrivalTime(LocalTime.parse(travelDetailsDTO.getArrivalTime()));
        travelDetails.setDepartureTime(LocalTime.parse(travelDetailsDTO.getDepartureTime()));
        travelDetails.setPartOfTheDayFrom(LocalTime.parse(travelDetailsDTO.getPartOfTheDayFrom()));
        travelDetails.setPartOfTheDayTo(LocalTime.parse(travelDetailsDTO.getPartOfTheDayTo()));


        if(travelDetailsDTO.getArrivalDate() != null  && !travelDetailsDTO.getArrivalDate().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate arrivalDate = LocalDate.parse(travelDetailsDTO.getArrivalDate(), formatter);
            travelDetails.setArrivalDate(arrivalDate);



        }


        if(travelDetailsDTO.getDepartureDate() != null && !travelDetailsDTO.getDepartureDate().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate departureDate = LocalDate.parse(travelDetailsDTO.getDepartureDate(), formatter);
            travelDetails.setDepartureDate(departureDate);

        }

        if(travelDetailsDTO.getAccommodationDetails()!=null) {
            travelDetails.setAccommodationDetails(accommodationDetailsMapper.accommodationDetailsDTOtoAccommodationDetails(travelDetailsDTO.getAccommodationDetails()));
        }
        return travelDetails;
    }

    public TravelDetails travelDetailsDTOToTravelDetailsNoId(TravelDetailsDTO travelDetailsDTO) {
        TravelDetails travelDetails = new TravelDetails();
        travelDetails.setAccountName(travelDetailsDTO.getAccountName());
        travelDetails.setAdditionalComments(travelDetailsDTO.getAdditionalComments());
        travelDetails.setApprovedBy(travelDetailsDTO.getApprovedBy());
        travelDetails.setTravelReqID(travelDetailsDTO.getTravelReqID());
        travelDetails.setArrivalCountry(travelDetailsDTO.getArrivalCountry());
        travelDetails.setArriveTo(travelDetailsDTO.getArriveTo());
        travelDetails.setBookingPurpose(travelDetailsDTO.getBookingPurpose());
        travelDetails.setDepartFrom(travelDetailsDTO.getDepartFrom());
        travelDetails.setDepartingCountry(travelDetailsDTO.getDepartingCountry());
        travelDetails.setEmployee(employeeMapper.employeeDTOtoEmployee(travelDetailsDTO.getEmployee()));
        travelDetails.setTripPaidBy(travelDetailsDTO.getTripPaidBy());
        travelDetails.setVisaDetail(travelDetailsDTO.getVisaDetail());
        travelDetails.setPreviousId(travelDetailsDTO.getPreviousId());
        travelDetails.setUpdateDate(travelDetailsDTO.getUpdateDate());
        travelDetails.setUpdateTime(travelDetailsDTO.getUpdateTime());
        travelDetails.setMailSent(travelDetailsDTO.getMailSent());


        if(travelDetailsDTO.getArrivalDate() != null  && !travelDetailsDTO.getArrivalDate().equals("")) {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate arrivalDate = LocalDate.parse(travelDetailsDTO.getArrivalDate(), formatter);
            travelDetails.setArrivalDate(arrivalDate);
        }

        if(travelDetailsDTO.getDepartureDate() != null && !travelDetailsDTO.getDepartureDate().equals("")) {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate departureDate = LocalDate.parse(travelDetailsDTO.getDepartureDate(), formatter);
            travelDetails.setDepartureDate(departureDate);
        }

        if(travelDetailsDTO.getArrivalTime()==null || travelDetailsDTO.getArrivalTime().equals("")){
            LocalTime arrivalTime = LocalTime.parse("00:00");
            travelDetails.setArrivalTime(arrivalTime);
        }
        else{
            LocalTime arrivalTime = LocalTime.parse(travelDetailsDTO.getArrivalTime());
            travelDetails.setArrivalTime(arrivalTime);
        }

        if(travelDetailsDTO.getDepartureTime()==null || travelDetailsDTO.getDepartureTime().equals("")){
            LocalTime departureTime = LocalTime.parse("00:00");
            travelDetails.setDepartureTime(departureTime);
        }
        else{
            LocalTime departureTime = LocalTime.parse(travelDetailsDTO.getDepartureTime());
            travelDetails.setDepartureTime(departureTime);
        }

        if(travelDetailsDTO.getPartOfTheDayFrom()==null || travelDetailsDTO.getPartOfTheDayFrom().equals("")){
            LocalTime partOfTheDayFrom = LocalTime.parse("00:00");
            travelDetails.setPartOfTheDayFrom(partOfTheDayFrom);
        }
        else{
            LocalTime partOfTheDayFrom = LocalTime.parse(travelDetailsDTO.getPartOfTheDayFrom());
            travelDetails.setPartOfTheDayFrom(partOfTheDayFrom);
        }

        if(travelDetailsDTO.getPartOfTheDayTo()==null || travelDetailsDTO.getPartOfTheDayTo().equals("")){
            LocalTime partOfTheDayTo = LocalTime.parse("00:00");
            travelDetails.setPartOfTheDayTo(partOfTheDayTo);
        }
        else{
            LocalTime partOfTheDayTo = LocalTime.parse(travelDetailsDTO.getPartOfTheDayTo());
            travelDetails.setPartOfTheDayTo(partOfTheDayTo);
        }

        if(travelDetailsDTO.getAccommodationDetails()!=null) {
            travelDetails.setAccommodationDetails(accommodationDetailsMapper.accommodationDetailsDTOtoAccommodationDetailsForTravel(travelDetailsDTO.getAccommodationDetails()));
        }
        return travelDetails;
    }

    public TravelDetails jsonDetailsToTravelDetails(JsonEmployeeTravelDetail jsonEmployeeTravelDetail) {
        TravelDetails travelDetails = new TravelDetails();

        travelDetails.setAccountName(jsonEmployeeTravelDetail.getAccountName());
        travelDetails.setAdditionalComments(jsonEmployeeTravelDetail.getAddtionalComments());
        travelDetails.setApprovedBy(jsonEmployeeTravelDetail.getApprovedBy());
        travelDetails.setTravelReqID(jsonEmployeeTravelDetail.getTravelReqID());
        travelDetails.setArrivalCountry(jsonEmployeeTravelDetail.getArrivalCountry());
        travelDetails.setArrivalDate(TimeAndDateParser.parseDate(jsonEmployeeTravelDetail.getArrivalDate()));
        travelDetails.setArrivalTime(TimeAndDateParser.parseTime(jsonEmployeeTravelDetail.getArrivalTime()));
        travelDetails.setArriveTo(jsonEmployeeTravelDetail.getArriveTo());
        travelDetails.setBookingPurpose(jsonEmployeeTravelDetail.getBookingPurpose());
        travelDetails.setDepartFrom(jsonEmployeeTravelDetail.getDepartFrom());
        travelDetails.setDepartingCountry(jsonEmployeeTravelDetail.getDepartingCountry());
        travelDetails.setDepartureDate(TimeAndDateParser.parseDate(jsonEmployeeTravelDetail.getDepartureDate()));
        travelDetails.setDepartureTime(TimeAndDateParser.parseTime(jsonEmployeeTravelDetail.getDepartureTime()));
        travelDetails.setEmployee(employeeService.findByEmployeeId(jsonEmployeeTravelDetail.getEmployeeID()));
        travelDetails.setTripPaidBy(jsonEmployeeTravelDetail.getTripPaidBy());
        travelDetails.setVisaDetail(jsonEmployeeTravelDetail.getVisaDetail());
        travelDetails.setMailSent(false);

        return travelDetails;
    }

    public Set<TravelDetailsDTO> getTravelDetailsDTOS(List<TravelDetails> travelDetailsList) {
        Set<TravelDetailsDTO> travelDetailsDTOS = new HashSet<>();
        for (TravelDetails travelDetails : travelDetailsList) {
            travelDetailsDTOS.add(travelDetailsToDTO(travelDetails));
        }
        return travelDetailsDTOS;
    }
}
