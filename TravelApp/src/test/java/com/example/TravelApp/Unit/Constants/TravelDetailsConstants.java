package com.example.TravelApp.Unit.Constants;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Model.TravelDetails;

import com.example.TravelApp.Mapper.EmployeeMapper;
import com.example.TravelApp.Mapper.TravelDetailsMapper;
import org.mockito.Mock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class TravelDetailsConstants {
    @Mock
    private EmployeeMapper employeeMapperMock;

    @Mock
    private TravelDetailsMapper travelDetailsMapperMock;

    public static final long ID = 1;
    public static final String DEPART_FROM = "Belgrade";
    public static final String ARRIVE_TO = "New York";
    public static final LocalDate DEPARTURE_DATE =LocalDate.now().plusDays(1);
    public static final String TRAVEL_REQ_ID = "TR020614";
    public static final String ACCOUNT_NAME = "Account name";
    public static final String APPROVED_BY = "012364";
    public static final String VISA_DETAIL = "no visa";
    public static final String TRIP_PAID_BY = "synechron d.o.o";
    public static final LocalTime ARRIVAL_TIME = LocalTime.now();
    public static final LocalDate ARRIVAL_DATE =LocalDate.now().plusDays(1);
    public static final LocalTime DEPARTURE_TIME = LocalTime.now().minusHours(1);
    public static final String DEPARTING_COUNTRY = "Serbia";
    public static final String ARRIVAL_COUNTRY = "USA";
    public static final String ADDITIONAL_COMMENTS = "no comments";
    public static final String BOOKING_PURPOSE = "events";
    public static final LocalTime PART_OF_THE_DAY_FROM = LocalTime.now();
    public static final LocalTime PART_OF_THE_DAY_TO = LocalTime.now();
    public static final String RETURN_TRIP = "TR020645";
    public static final long PREVIOUS_ID = 0;
    public static final String UPDATE_DATE = "";
    public static final String UPDATE_TIME = "";
    public static final boolean MAIL_SENT = false;


    public static final long ID_2 = 2;
    public static final String DEPART_FROM_2 = "Belgrade";
    public static final String ARRIVE_TO_2 = "Mumbai";
    public static final LocalDate DEPARTURE_DATE_2 =LocalDate.now().plusDays(2);
    public static final String TRAVEL_REQ_ID_2 = "TR020614";
    public static final String ACCOUNT_NAME_2 = "Account name";
    public static final String APPROVED_BY_2 = "012364";
    public static final String VISA_DETAIL_2 = "no visa";
    public static final String TRIP_PAID_BY_2 = "synechron d.o.o";
    public static final LocalTime ARRIVAL_TIME_2 = LocalTime.now();
    public static final LocalDate ARRIVAL_DATE_2 =LocalDate.now().plusDays(3);
    public static final LocalTime DEPARTURE_TIME_2 = LocalTime.now().minusHours(1);
    public static final String DEPARTING_COUNTRY_2 = "Serbia";
    public static final String ARRIVAL_COUNTRY_2 = "India";
    public static final String ADDITIONAL_COMMENTS_2 = "no comments";
    public static final String BOOKING_PURPOSE_2 = "events";
    public static final LocalTime PART_OF_THE_DAY_FROM_2 = LocalTime.now();
    public static final LocalTime PART_OF_THE_DAY_TO_2 = LocalTime.now();
    public static final String RETURN_TRIP_2 = "";
    public static final long PREVIOUS_ID_2 = 0;
    public static final String UPDATE_DATE_2 = "";
    public static final String UPDATE_TIME_2 = "";
    public static final boolean MAIL_SENT_2 = false;

    public static final TravelDetailsDTO getTravelDetailsDTO() {
        EmployeeDTO employee = EmployeeConstants.EMPLOYEE_DTO;
        AccommodationDetailsDTO accommodationDetails = AccommodationConstants.ACCOMMODATION_DETAILSDTO;

        return new TravelDetailsDTO(ID,DEPART_FROM ,ARRIVE_TO,DEPARTURE_DATE.toString(),TRAVEL_REQ_ID,ACCOUNT_NAME,APPROVED_BY, employee,
                VISA_DETAIL,TRIP_PAID_BY,ARRIVAL_TIME.toString(),ARRIVAL_DATE.toString(),DEPARTURE_TIME.toString(),DEPARTING_COUNTRY,ARRIVAL_COUNTRY,ADDITIONAL_COMMENTS,BOOKING_PURPOSE,PART_OF_THE_DAY_FROM.toString(), PART_OF_THE_DAY_TO.toString(),
                accommodationDetails, PREVIOUS_ID, UPDATE_DATE.toString(), UPDATE_TIME.toString(), RETURN_TRIP, accommodationDetails.getId(), MAIL_SENT);
    }

    public static final TravelDetailsDTO getTravelDetailsDTOForAccommodation() {
        EmployeeDTO employee = EmployeeConstants.EMPLOYEE_DTO3;
        AccommodationDetailsDTO accommodationDetails = new AccommodationDetailsDTO();

        return new TravelDetailsDTO(ID,DEPART_FROM ,ARRIVE_TO,DEPARTURE_DATE.toString(),TRAVEL_REQ_ID,ACCOUNT_NAME,APPROVED_BY, employee,
                VISA_DETAIL,TRIP_PAID_BY,ARRIVAL_TIME.toString(),"01/01/2021",DEPARTURE_TIME.toString(),DEPARTING_COUNTRY,ARRIVAL_COUNTRY,ADDITIONAL_COMMENTS,BOOKING_PURPOSE,PART_OF_THE_DAY_FROM.toString(), PART_OF_THE_DAY_TO.toString(),
                accommodationDetails, PREVIOUS_ID, UPDATE_DATE.toString(), UPDATE_TIME.toString(), RETURN_TRIP, accommodationDetails.getId(), MAIL_SENT);
    }

    public static final TravelDetails getTravelDetails() {
        Employee employee = new Employee();
        AccommodationDetails accommodationDetails = new AccommodationDetails();
        return new TravelDetails(ID,DEPART_FROM ,ARRIVE_TO,DEPARTURE_DATE,TRAVEL_REQ_ID,ACCOUNT_NAME,APPROVED_BY, employee,
                VISA_DETAIL,TRIP_PAID_BY,ARRIVAL_TIME,ARRIVAL_DATE,DEPARTURE_TIME,DEPARTING_COUNTRY,ARRIVAL_COUNTRY,ADDITIONAL_COMMENTS,BOOKING_PURPOSE,PART_OF_THE_DAY_FROM, PART_OF_THE_DAY_TO,
                RETURN_TRIP,PREVIOUS_ID,UPDATE_DATE, UPDATE_TIME,MAIL_SENT,accommodationDetails);
    }

    public static Set<TravelDetails> getTravelDetailsSet() {
        Employee employee = new Employee();
        AccommodationDetails accommodationDetails = new AccommodationDetails();
        Set<TravelDetails> travelDetailsSet = new HashSet<>();

        TravelDetails travelDetails =new TravelDetails(ID,DEPART_FROM ,ARRIVE_TO,DEPARTURE_DATE,TRAVEL_REQ_ID,ACCOUNT_NAME,APPROVED_BY, employee,
                VISA_DETAIL,TRIP_PAID_BY,ARRIVAL_TIME,ARRIVAL_DATE,DEPARTURE_TIME,DEPARTING_COUNTRY,ARRIVAL_COUNTRY,ADDITIONAL_COMMENTS,BOOKING_PURPOSE,PART_OF_THE_DAY_FROM, PART_OF_THE_DAY_TO,
                RETURN_TRIP,PREVIOUS_ID,UPDATE_DATE, UPDATE_TIME,MAIL_SENT,accommodationDetails);

        TravelDetails travelDetails2 =new TravelDetails(ID_2,DEPART_FROM_2 ,ARRIVE_TO_2,DEPARTURE_DATE_2,TRAVEL_REQ_ID_2,ACCOUNT_NAME_2,APPROVED_BY_2, employee,
                VISA_DETAIL_2,TRIP_PAID_BY_2,ARRIVAL_TIME_2,ARRIVAL_DATE_2,DEPARTURE_TIME_2,DEPARTING_COUNTRY_2,ARRIVAL_COUNTRY_2,ADDITIONAL_COMMENTS_2,
                BOOKING_PURPOSE_2,PART_OF_THE_DAY_FROM_2, PART_OF_THE_DAY_TO_2,
                RETURN_TRIP_2,PREVIOUS_ID_2,UPDATE_DATE_2, UPDATE_TIME_2,MAIL_SENT_2,accommodationDetails);

        travelDetailsSet.add(travelDetails);
        travelDetailsSet.add(travelDetails2);

        return travelDetailsSet;
    }
    public static TravelDetails getTravelDetailsForCreate() {
        Employee employee = new Employee(1,"123460","email@gmail.com","First name", "Last name","Designation name",null,null,null,null);
        AccommodationDetails accommodationDetails = new AccommodationDetails();
        TravelDetails travelDetails = new TravelDetails();
        travelDetails.setDepartFrom(DEPART_FROM);
        travelDetails.setArriveTo(ARRIVE_TO);
        travelDetails.setDepartureDate(DEPARTURE_DATE);
        travelDetails.setTravelReqID(TRAVEL_REQ_ID);
        travelDetails.setAccountName(ACCOUNT_NAME);
        travelDetails.setApprovedBy(APPROVED_BY);
        travelDetails.setEmployee(employee);
        travelDetails.setVisaDetail(VISA_DETAIL);

        travelDetails.setTripPaidBy(TRIP_PAID_BY);
        travelDetails.setArrivalTime(ARRIVAL_TIME);
        travelDetails.setArrivalDate(ARRIVAL_DATE);
        travelDetails.setDepartureTime(DEPARTURE_TIME);
        travelDetails.setDepartingCountry(DEPARTING_COUNTRY);
        travelDetails.setArrivalCountry(ARRIVAL_COUNTRY);
        travelDetails.setAdditionalComments(ADDITIONAL_COMMENTS);
        travelDetails.setBookingPurpose(BOOKING_PURPOSE);

        travelDetails.setPartOfTheDayFrom(PART_OF_THE_DAY_FROM);
        travelDetails.setPartOfTheDayTo(PART_OF_THE_DAY_TO);
        travelDetails.setReturnTrip(RETURN_TRIP);
        travelDetails.setPreviousId(PREVIOUS_ID);
        travelDetails.setUpdateDate(UPDATE_DATE);
        travelDetails.setUpdateTime(UPDATE_TIME);
        travelDetails.setMailSent(MAIL_SENT);
        travelDetails.setAccommodationDetails(accommodationDetails);

        return travelDetails;
    }

}
