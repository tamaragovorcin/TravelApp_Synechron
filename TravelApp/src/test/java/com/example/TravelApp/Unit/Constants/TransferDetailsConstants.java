package com.example.TravelApp.Unit.Constants;

import com.example.TravelApp.DTOs.EmailDTO;
import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TransferDetailsReserveDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Mapper.EmployeeMapper;
import com.example.TravelApp.Mapper.TransferDetailsMapper;
import com.example.TravelApp.Model.*;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransferDetailsConstants {
    @Mock
    private EmployeeMapper employeeMapperMock;

    @Mock
    private TransferDetailsMapper travelDetailsMapperMock;

    public static final long ID = 1;
    public static final String DEPART_FROM = "Belgrade";
    public static final String FLIGHT_ID = "1";
    public static final String ARRIVE_TO = "New York";
    public static final LocalDate DEPARTURE_DATE =LocalDate.now().plusDays(1);
    public static final String TRAVEL_REQ_ID = "TR020614";
    public static final LocalTime ARRIVAL_TIME = LocalTime.now();
    public static final LocalDate ARRIVAL_DATE =LocalDate.now().plusDays(1);
    public static final LocalTime RESERVATION_TIME = null;
    public static final LocalDate RESERVATION_DATE =null;
    public static final LocalTime TRANSFER_TIME = null;
    public static final LocalDate TRANSFER_DATE =null;
    public static final LocalTime DEPARTURE_TIME = LocalTime.now().minusHours(1);
    public static final String TRANSFER_ADDRESS = null;
    public static final String TRANSFER_CITY = null;
    public static final String DEPARTING_COUNTRY = "Serbia";
    public static final String ARRIVAL_COUNTRY = "USA";
    public static final String ADDITIONAL_COMMENTS = null;
    public static final String PREVIOUS_ID = null;
    public static final LocalDate UPDATE_DATE = null;
    public static final LocalTime UPDATE_TIME = null;

    public static final long ID2 = 2;
    public static final String DEPART_FROM2 = "Belgrade";
    public static final String FLIGHT_ID2 = "3";
    public static final String ARRIVE_TO2 = "Paris";
    public static final LocalDate DEPARTURE_DATE2 =LocalDate.now().plusDays(1);
    public static final String TRAVEL_REQ_ID2 = "TR020615";
    public static final LocalTime ARRIVAL_TIME2 = LocalTime.now();
    public static final LocalDate ARRIVAL_DATE2 =LocalDate.now().plusDays(10);
    public static final LocalTime RESERVATION_TIME2 = LocalTime.now();
    public static final LocalDate RESERVATION_DATE2 =LocalDate.now();
    public static final LocalTime TRANSFER_TIME2 = LocalTime.now();
    public static final LocalDate TRANSFER_DATE2 =LocalDate.now().plusDays(10);
    public static final LocalTime DEPARTURE_TIME2 = LocalTime.now().minusHours(1);
    public static final String TRANSFER_ADDRESS2 = "Bulevar oslobodjenja 4";
    public static final String TRANSFER_CITY2 = "Novi Sad";
    public static final String DEPARTING_COUNTRY2 = "Serbia";
    public static final String ARRIVAL_COUNTRY2 = "France";
    public static final String ADDITIONAL_COMMENTS2 = "no comments";
    public static final String PREVIOUS_ID2 = null;
    public static final LocalDate UPDATE_DATE2 = null;
    public static final LocalTime UPDATE_TIME2 = null;

    public static final long ID3 = 3;
    public static final String DEPART_FROM3 = "Belgrade";
    public static final String FLIGHT_ID3 = "3";
    public static final String ARRIVE_TO3 = "Lisabon";
    public static final LocalDate DEPARTURE_DATE3 =LocalDate.now().plusDays(1);
    public static final String TRAVEL_REQ_ID3 = "TR020616";
    public static final LocalTime ARRIVAL_TIME3 = LocalTime.now();
    public static final LocalDate ARRIVAL_DATE3 =LocalDate.now().plusDays(10);
    public static final LocalTime RESERVATION_TIME3 = LocalTime.now();
    public static final LocalDate RESERVATION_DATE3 =LocalDate.now();
    public static final LocalTime TRANSFER_TIME3 = LocalTime.now();
    public static final LocalDate TRANSFER_DATE3 =LocalDate.now().plusDays(10);
    public static final LocalTime DEPARTURE_TIME3 = LocalTime.now().minusHours(1);
    public static final String TRANSFER_ADDRESS3 = "Bulevar oslobodjenja 4";
    public static final String TRANSFER_CITY3 = "Novi Sad";
    public static final String DEPARTING_COUNTRY3 = "Serbia";
    public static final String ARRIVAL_COUNTRY3 = "Portugal";
    public static final String ADDITIONAL_COMMENTS3 = "no comments";
    public static final String PREVIOUS_ID3 = null;
    public static final LocalDate UPDATE_DATE3 = null;
    public static final LocalTime UPDATE_TIME3 = null;

    public static final long ID4 = 4;
    public static final String DEPART_FROM4 = "Belgrade";
    public static final String FLIGHT_ID4 = "4";
    public static final String ARRIVE_TO4 = "Rome";
    public static final LocalDate DEPARTURE_DATE4 =LocalDate.now().plusDays(1);
    public static final String TRAVEL_REQ_ID4 = "TR020617";
    public static final LocalTime ARRIVAL_TIME4 = LocalTime.now();
    public static final LocalDate ARRIVAL_DATE4 =LocalDate.now().plusDays(10);
    public static final LocalTime RESERVATION_TIME4 = LocalTime.now();
    public static final LocalDate RESERVATION_DATE4 =LocalDate.now();
    public static final LocalTime TRANSFER_TIME4 = LocalTime.now();
    public static final LocalDate TRANSFER_DATE4 =LocalDate.now().plusDays(10);
    public static final LocalTime DEPARTURE_TIME4 = LocalTime.now().minusHours(1);
    public static final String TRANSFER_ADDRESS4 = "Bulevar oslobodjenja 4";
    public static final String TRANSFER_CITY4 = "Novi Sad";
    public static final String DEPARTING_COUNTRY4 = "Serbia";
    public static final String ARRIVAL_COUNTRY4 = "Italy";
    public static final String ADDITIONAL_COMMENTS4 = "no comments";
    public static final String PREVIOUS_ID4 = null;
    public static final LocalDate UPDATE_DATE4 = null;
    public static final LocalTime UPDATE_TIME4 = null;

    public static List<TransferDetails> getTransferDetailsList(){
        Employee employee = new Employee();
        List<TransferDetails> transferDetailsList = new ArrayList<>();
        TransferDetails transferDetails = new TransferDetails(ID, TRAVEL_REQ_ID, FLIGHT_ID, RESERVATION_DATE, RESERVATION_TIME,
               ADDITIONAL_COMMENTS, DEPARTING_COUNTRY, DEPART_FROM, ARRIVAL_COUNTRY, ARRIVE_TO, DEPARTURE_DATE, DEPARTURE_TIME,
                ARRIVAL_DATE, ARRIVAL_TIME, PREVIOUS_ID, UPDATE_DATE, UPDATE_TIME, TRANSFER_DATE, TRANSFER_TIME, TRANSFER_CITY,
                TRANSFER_ADDRESS, employee);

        TransferDetails transferDetails2 = new TransferDetails(ID2, TRAVEL_REQ_ID2, FLIGHT_ID2, RESERVATION_DATE2, RESERVATION_TIME2,
                ADDITIONAL_COMMENTS2, DEPARTING_COUNTRY2, DEPART_FROM2, ARRIVAL_COUNTRY2, ARRIVE_TO2, DEPARTURE_DATE2, DEPARTURE_TIME2,
                ARRIVAL_DATE2, ARRIVAL_TIME2, PREVIOUS_ID2, UPDATE_DATE2, UPDATE_TIME2, TRANSFER_DATE2, TRANSFER_TIME2, TRANSFER_CITY2,
                TRANSFER_ADDRESS2, employee);

        transferDetailsList.add(transferDetails);
        transferDetailsList.add(transferDetails2);
        return transferDetailsList;
    }

    public static Set<TransferDetails> getTransferDetailsSet(){
        Employee employee = new Employee();
        Set<TransferDetails> transferDetailsSet = new HashSet<>();
        TransferDetails transferDetails = new TransferDetails(ID, TRAVEL_REQ_ID, FLIGHT_ID, RESERVATION_DATE, RESERVATION_TIME,
                ADDITIONAL_COMMENTS, DEPARTING_COUNTRY, DEPART_FROM, ARRIVAL_COUNTRY, ARRIVE_TO, DEPARTURE_DATE, DEPARTURE_TIME,
                ARRIVAL_DATE, ARRIVAL_TIME, PREVIOUS_ID, UPDATE_DATE, UPDATE_TIME, TRANSFER_DATE, TRANSFER_TIME, TRANSFER_CITY,
                TRANSFER_ADDRESS, employee);

        TransferDetails transferDetails2 = new TransferDetails(ID2, TRAVEL_REQ_ID2, FLIGHT_ID2, RESERVATION_DATE2, RESERVATION_TIME2,
                ADDITIONAL_COMMENTS2, DEPARTING_COUNTRY2, DEPART_FROM2, ARRIVAL_COUNTRY2, ARRIVE_TO2, DEPARTURE_DATE2, DEPARTURE_TIME2,
                ARRIVAL_DATE2, ARRIVAL_TIME2, PREVIOUS_ID2, UPDATE_DATE2, UPDATE_TIME2, TRANSFER_DATE2, TRANSFER_TIME2, TRANSFER_CITY2,
                TRANSFER_ADDRESS2, employee);

        transferDetailsSet.add(transferDetails);
        transferDetailsSet.add(transferDetails2);
        return transferDetailsSet;
    }

    public static List<TransferDetails> getReservedTransferDetailsList(){
        Employee employee = new Employee();
        List<TransferDetails> transferDetailsList = new ArrayList<>();
        TransferDetails transferDetails = new TransferDetails(ID3, TRAVEL_REQ_ID3, FLIGHT_ID3, RESERVATION_DATE3, RESERVATION_TIME3,
                ADDITIONAL_COMMENTS3, DEPARTING_COUNTRY3, DEPART_FROM3, ARRIVAL_COUNTRY3, ARRIVE_TO3, DEPARTURE_DATE3, DEPARTURE_TIME3,
                ARRIVAL_DATE3, ARRIVAL_TIME3, PREVIOUS_ID3, UPDATE_DATE3, UPDATE_TIME3, TRANSFER_DATE3, TRANSFER_TIME3, TRANSFER_CITY3,
                TRANSFER_ADDRESS3, employee);

        TransferDetails transferDetails2 = new TransferDetails(ID4, TRAVEL_REQ_ID4, FLIGHT_ID4, RESERVATION_DATE4, RESERVATION_TIME4,
                ADDITIONAL_COMMENTS4, DEPARTING_COUNTRY4, DEPART_FROM4, ARRIVAL_COUNTRY4, ARRIVE_TO4, DEPARTURE_DATE4, DEPARTURE_TIME4,
                ARRIVAL_DATE4, ARRIVAL_TIME4, PREVIOUS_ID4, UPDATE_DATE4, UPDATE_TIME4, TRANSFER_DATE4, TRANSFER_TIME4, TRANSFER_CITY4,
                TRANSFER_ADDRESS4, employee);

        transferDetailsList.add(transferDetails);
        transferDetailsList.add(transferDetails2);
        return transferDetailsList;
    }

    public static Set<TransferDetails> getTransferDetails(){
        Employee employee = new Employee();
        Set<TransferDetails> transferDetailsSet = new HashSet<>();
        TransferDetails transferDetails = new TransferDetails(ID, TRAVEL_REQ_ID, FLIGHT_ID, TimeAndDateParser.parseDate("2021-01-01"), TimeAndDateParser.parseTime("00:00:00"),
                ADDITIONAL_COMMENTS, DEPARTING_COUNTRY, DEPART_FROM, ARRIVAL_COUNTRY, ARRIVE_TO, DEPARTURE_DATE, DEPARTURE_TIME,
                ARRIVAL_DATE, ARRIVAL_TIME, PREVIOUS_ID, UPDATE_DATE, UPDATE_TIME, TRANSFER_DATE, TRANSFER_TIME, TRANSFER_CITY,
                TRANSFER_ADDRESS, employee);
        transferDetailsSet.add(transferDetails);
        return transferDetailsSet;
    }

    public static TransferDetails getOneTransferDetails(){
        Employee employee = new Employee();
        TransferDetails transferDetails = new TransferDetails(ID, TRAVEL_REQ_ID, FLIGHT_ID, RESERVATION_DATE, RESERVATION_TIME,
                ADDITIONAL_COMMENTS, DEPARTING_COUNTRY, DEPART_FROM, ARRIVAL_COUNTRY, ARRIVE_TO, DEPARTURE_DATE, DEPARTURE_TIME,
                ARRIVAL_DATE, ARRIVAL_TIME, PREVIOUS_ID, UPDATE_DATE, UPDATE_TIME, TRANSFER_DATE, TRANSFER_TIME, TRANSFER_CITY,
                TRANSFER_ADDRESS, employee);
        return transferDetails;
    }

    public static Set<TransferDetailsDTO> getOneReservedTransferDetailsDTO(){
        EmployeeDTO employee = new EmployeeDTO();
        TransferDetailsDTO transferDetailsDTO = new TransferDetailsDTO(ID, TRAVEL_REQ_ID, FLIGHT_ID, "2021-01-01", "00:00:00",
                DEPART_FROM, ARRIVE_TO, "2021-01-01", employee, "00:00:00", "2021-01-01", "00:00:00", DEPARTING_COUNTRY, ARRIVAL_COUNTRY, ADDITIONAL_COMMENTS,
                PREVIOUS_ID, null, null, null, null, null,
                null, null);
        Set<TransferDetailsDTO> transferDetailsSetDTOS = new HashSet<>();
        transferDetailsSetDTOS.add(transferDetailsDTO);

        return transferDetailsSetDTOS;
    }


    public static Set<TransferDetailsReserveDTO> getTransferDetailsReserveDTO(){
        Set<TransferDetailsReserveDTO> transferDetailsReserveDTOS = new HashSet<>();
        TransferDetailsReserveDTO transferDetailsReserveDTO = new TransferDetailsReserveDTO(1, "2021-01-01", "00:00:00",
                null,null,null,null, null);
        transferDetailsReserveDTOS.add(transferDetailsReserveDTO);
        return transferDetailsReserveDTOS;
    }

    public static EmailDTO getEmailDTO() {
        EmailDTO emailDTO = new EmailDTO();
        Set<TransferDetailsDTO> transferDetailsSetDTO = getOneReservedTransferDetailsDTO();
        List<TransferDetailsDTO> transferDetailsDTOS = new ArrayList<>();
        for(TransferDetailsDTO transferDetailsDTO : transferDetailsSetDTO){
            transferDetailsDTOS.add(transferDetailsDTO);
        }
        emailDTO.setId(transferDetailsDTOS.get(0).getId());
        emailDTO.setEmployee(transferDetailsDTOS.get(0).getEmployee());
        emailDTO.setDepartFrom(transferDetailsDTOS.get(0).getDepartFrom());
        emailDTO.setReservationDate(transferDetailsDTOS.get(0).getReservationDate());
        emailDTO.setReservationTime(transferDetailsDTOS.get(0).getReservationTime());
        return emailDTO;
    }
}
