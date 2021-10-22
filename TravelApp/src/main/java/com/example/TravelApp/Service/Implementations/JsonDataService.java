package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.*;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Repository.TravelDetailsRepository;
import com.example.TravelApp.Service.Interfaces.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JsonDataService implements IJsonDataService {
    private final String URI_AUTHENTICATE = "http://172.20.50.184:8099/authenticate";
    private final String URI_GET_DATA = "http://172.20.50.184:8099/gettraveldata";

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IAccommodationDetailsService accommodationDetailsService;

    @Autowired
    ITravelDetailsService travelDetailsService;

    @Autowired
    ITransferDetailsService transferDetailsService;

    @Autowired
    IDecisionForABusinessTripService decisionForABusinessTripService;

    @Autowired
    RestTemplate restTemplate;

    public void getTravelData() throws JsonProcessingException {
        TokenDTO token = getToken();
        JSONObject jsonFile = getTravelDataAsJson(token);
        parseJsonFile(jsonFile);
    }

    public TokenDTO getToken() {
        TravelDataAuthenticate travelDataAuthenticate= new TravelDataAuthenticate("syneserbia", "$yne$erbi@");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TravelDataAuthenticate> entity = new HttpEntity<TravelDataAuthenticate>(travelDataAuthenticate,headers);

        return restTemplate.exchange(URI_AUTHENTICATE, HttpMethod.POST, entity, TokenDTO.class).getBody();
    }

    public JSONObject getTravelDataAsJson(TokenDTO token) {
        TravelDataPeriodDTO travelDataPeriodDTO = getTravelDataPeriodDTO();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token.getToken());
        HttpEntity<TravelDataPeriodDTO> entity = new HttpEntity<TravelDataPeriodDTO>(travelDataPeriodDTO,headers);

        return restTemplate.exchange(URI_GET_DATA, HttpMethod.POST, entity, JSONObject.class).getBody();
    }

    private TravelDataPeriodDTO getTravelDataPeriodDTO() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = currentDate.plus(1, ChronoUnit.WEEKS);
        String startDateString = startDate.format(formatter);

        LocalDate endDate = currentDate.plus(-1, ChronoUnit.WEEKS);
        String endDateString = endDate.format(formatter);

        //2019-11-11, 2020-12-31
        TravelDataPeriodDTO travelDataPeriodDTO= new TravelDataPeriodDTO("2019-11-11", "2021-12-31");
        return travelDataPeriodDTO;
    }

    public void parseJsonFile(JSONObject jsonObject) throws JsonProcessingException {
        JsonFileDTO jsonFileDTO = new ObjectMapper().readValue(jsonObject.toJSONString(), JsonFileDTO.class);
        employeeService.parseEmployeeFromJson(jsonFileDTO.getEmployee_details());
        transferDetailsService.parseTransferDetails(jsonFileDTO.getEmployee_travel_details());
        Set<AccommodationDetailsDTO> accommodationDetailsDTOs =
                accommodationDetailsService.parseAccommodationDetails(jsonFileDTO.getEmployee_accommodation_details());
        Set<TravelDetailsDTO> travelDetailsDTOs = travelDetailsService.parseTravelDetails(jsonFileDTO.getEmployee_travel_details());

        for(TravelDetailsDTO travelDetailsDTO : travelDetailsDTOs){
            travelDetailsService.isCircularTravel(travelDetailsDTO.getTravelReqID());
            for(AccommodationDetailsDTO accommodationDetailsDTO : accommodationDetailsDTOs){
                if(accommodationDetailsService.accommodationAndTravelCompatibility(accommodationDetailsDTO, travelDetailsDTO) != null){
                    travelDetailsService.addAccommodationDetailsToTravelDetails(travelDetailsDTO.getId(), accommodationDetailsDTO.getId());
                }
            }
        }
        decisionForABusinessTripService.scheduleDecisionSaving(travelDetailsDTOs);
    }
}
