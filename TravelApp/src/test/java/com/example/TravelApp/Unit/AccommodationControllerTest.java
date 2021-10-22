package com.example.TravelApp.Unit;

import com.example.TravelApp.Controllers.AccomodationDetailsController;
import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Mapper.AccommodationDetailsMapper;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Service.Implementations.AccommodationDetailsServiceImpl;

import com.example.TravelApp.Service.Implementations.TravelDetailsServiceImpl;
import com.example.TravelApp.Unit.Constants.TravelDetailsConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.TravelApp.Unit.Constants.AccommodationConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccommodationControllerTest {

    @Mock
    private AccommodationDetailsServiceImpl accommodationDetailsServiceMock;

    @InjectMocks
    private AccomodationDetailsController accomodationDetailsControllerMock;

    @Mock
    private AccommodationDetailsMapper accommodationDetailsMapperMock;


    @InjectMocks
    private AccommodationDetailsMapper accommodationDetailsMapper;

    @Mock
    private TravelDetailsServiceImpl travelDetailsService;




    @Test
    public void testShouldFindAllAccommodations()  {

        List<AccommodationDetails> accommodationDetails = new ArrayList<>(getAccommodationDetailsSet());
        Set<AccommodationDetailsDTO> accommodationDetailsDTOS = accommodationDetailsMapperMock.getAccommodationsDTOS(accommodationDetails);
        when(accommodationDetailsServiceMock.findAll()).thenReturn(accommodationDetailsDTOS);

        ResponseEntity<Set<AccommodationDetailsDTO>> result =  accomodationDetailsControllerMock.findAll();

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(),accommodationDetailsDTOS);

    }

    @Test
    public void testShouldCreateAccommodation() {

        List<AccommodationDetails> accommodationDetailsList = new ArrayList<>(getAccommodationDetailsSet());
        Set<AccommodationDetailsDTO> accommodationDetailsDTOSSet = accommodationDetailsMapperMock.getAccommodationsDTOS(accommodationDetailsList);
        AccommodationDetailsDTO accommodationDetailsDTO = accommodationDetailsMapperMock.accommodationDetailsToDTO(ACCOMMODATION_DETAILS);
        when(accommodationDetailsServiceMock.findAll()).thenReturn(accommodationDetailsDTOSSet);
        when(accommodationDetailsServiceMock.save(accommodationDetailsDTO)).thenReturn(accommodationDetailsDTO);

        int dbSizeBeforeCreate = accommodationDetailsServiceMock.findAll().size();
        ResponseEntity<AccommodationDetailsDTO> result = accomodationDetailsControllerMock.create(accommodationDetailsDTO);

        accommodationDetailsList.add(accommodationDetailsMapperMock.accommodationDetailsDTOtoAccommodationDetails(result.getBody()));
        accommodationDetailsDTOSSet.add(result.getBody());
        when(accommodationDetailsServiceMock.findAll()).thenReturn(accommodationDetailsDTOSSet);

        assertEquals(accommodationDetailsServiceMock.findAll().size(),dbSizeBeforeCreate + 1);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        verify(accommodationDetailsServiceMock, times(2)).findAll();
        verify(accommodationDetailsServiceMock, times(1)).save(accommodationDetailsDTO);

    }


    @Test
    public void testShouldUpdateAccommodation() {

        // 1. Definisanje ponašanja
        when(accommodationDetailsServiceMock.findById(ID)).thenReturn(ACCOMMODATION_DETAILS_DTO);
        when(travelDetailsService.findById(ID)).thenReturn(TravelDetailsConstants.getTravelDetailsDTO());

        // 2. Akcija
        AccommodationDetailsDTO accommodationDetailsForUpdate = accommodationDetailsServiceMock.findById(ID);
        accommodationDetailsForUpdate.setBillingDetails(NEW_BILLING_DETAILS);
        accommodationDetailsForUpdate.setBookingPurpose(NEW_BOOKING_PURPOSE);

        AccommodationDetails accommodationDetails = new AccommodationDetails();
        accommodationDetails.setId(accommodationDetailsForUpdate.getId());
        accommodationDetails.setUpdateTime(accommodationDetailsForUpdate.getUpdateTime());
        accommodationDetails.setUpdateDate(accommodationDetailsForUpdate.getUpdateDate());
        accommodationDetails.setPreviousId(accommodationDetailsForUpdate.getPreviousId());
        accommodationDetails.setBillingDetails(accommodationDetailsForUpdate.getBillingDetails());
        accommodationDetails.setAccommodationRequestNo(accommodationDetailsForUpdate.getAccommodationRequestNo());
        accommodationDetails.setBookingPurpose(accommodationDetailsForUpdate.getBookingPurpose());
        accommodationDetails.setAccountName(accommodationDetailsForUpdate.getAccountName());
        accommodationDetails.setCheckInDate(LocalDateTime.parse(accommodationDetailsForUpdate.getCheckInDate()));
        accommodationDetails.setCheckOutDate(LocalDateTime.parse(accommodationDetailsForUpdate.getCheckOutDate()));
       accommodationDetails.setEmployee(EMPLOYEE);


        Set<TravelDetailsDTO> travelDetailsDTOS = new HashSet<>();
        for(TravelDetailsDTO travelDetails1:  accommodationDetailsForUpdate.getTravelDetails()){
            TravelDetailsDTO travelDetailsDTO = travelDetailsService.findById(travelDetails1.getId());
            travelDetailsDTO.setAccommodationDetails(accommodationDetailsMapper.accommodationDetailsToDTONoId(accommodationDetails));
            travelDetailsDTOS.add(travelDetailsDTO);

        }


        when(travelDetailsService.findAll()).thenReturn(travelDetailsDTOS);
        Set<TravelDetailsDTO> travelDetailsDTOS1 = accommodationDetailsServiceMock.update(accommodationDetailsForUpdate);

        // 3. Verifikacija
        assertThat(travelDetailsDTOS1).isNotNull();

        accommodationDetailsForUpdate = accommodationDetailsServiceMock.findById(ID);
        assertThat(accommodationDetailsForUpdate.getBillingDetails()).isEqualTo(NEW_BILLING_DETAILS);
        assertThat(accommodationDetailsForUpdate.getBookingPurpose()).isEqualTo(NEW_BOOKING_PURPOSE);
        verify(accommodationDetailsServiceMock, times(2)).findById(ID);
        verify(accommodationDetailsServiceMock, times(1)).update(accommodationDetailsForUpdate);
        verifyNoMoreInteractions(accommodationDetailsServiceMock);

    }

    @Test(expected = NullPointerException.class)
    public void testShouldNotUpdateAccommodation() {

        // 1. Definisanje ponašanja
        when(accommodationDetailsServiceMock.findById(ID)).thenReturn(ACCOMMODATION_DETAILS_DTO);
        when(travelDetailsService.findById(ID)).thenReturn(TravelDetailsConstants.getTravelDetailsDTO());

        // 2. Akcija
        AccommodationDetailsDTO accommodationDetailsForUpdate = accommodationDetailsServiceMock.findById(ID2);
        accommodationDetailsForUpdate.setBillingDetails(NEW_BILLING_DETAILS);
        accommodationDetailsForUpdate.setBookingPurpose(NEW_BOOKING_PURPOSE);

        AccommodationDetails accommodationDetails = new AccommodationDetails();
        accommodationDetails.setId(accommodationDetailsForUpdate.getId());
        accommodationDetails.setUpdateTime(accommodationDetailsForUpdate.getUpdateTime());
        accommodationDetails.setUpdateDate(accommodationDetailsForUpdate.getUpdateDate());
        accommodationDetails.setPreviousId(accommodationDetailsForUpdate.getPreviousId());
        accommodationDetails.setBillingDetails(accommodationDetailsForUpdate.getBillingDetails());
        accommodationDetails.setAccommodationRequestNo(accommodationDetailsForUpdate.getAccommodationRequestNo());
        accommodationDetails.setBookingPurpose(accommodationDetailsForUpdate.getBookingPurpose());
        accommodationDetails.setAccountName(accommodationDetailsForUpdate.getAccountName());
        accommodationDetails.setCheckInDate(LocalDateTime.parse(accommodationDetailsForUpdate.getCheckInDate()));
        accommodationDetails.setCheckOutDate(LocalDateTime.parse(accommodationDetailsForUpdate.getCheckOutDate()));
        accommodationDetails.setEmployee(EMPLOYEE);


        Set<TravelDetailsDTO> travelDetailsDTOS = new HashSet<>();
        for(TravelDetailsDTO travelDetails1:  accommodationDetailsForUpdate.getTravelDetails()){
            TravelDetailsDTO travelDetailsDTO = travelDetailsService.findById(travelDetails1.getId());
            travelDetailsDTO.setAccommodationDetails(accommodationDetailsMapper.accommodationDetailsToDTONoId(accommodationDetails));
            travelDetailsDTOS.add(travelDetailsDTO);

        }


        when(travelDetailsService.findAll()).thenReturn(travelDetailsDTOS);
        Set<TravelDetailsDTO> travelDetailsDTOS1 = accommodationDetailsServiceMock.update(accommodationDetailsForUpdate);

        // 3. Verifikacija
        assertThat(travelDetailsDTOS1).isNotNull();

        accommodationDetailsForUpdate = accommodationDetailsServiceMock.findById(ID);
        assertThat(accommodationDetailsForUpdate.getBillingDetails()).isEqualTo(NEW_BILLING_DETAILS);
        assertThat(accommodationDetailsForUpdate.getBookingPurpose()).isEqualTo(NEW_BOOKING_PURPOSE);
        verify(accommodationDetailsServiceMock, times(2)).findById(ID);
        verify(accommodationDetailsServiceMock, times(1)).update(accommodationDetailsForUpdate);
        verifyNoMoreInteractions(accommodationDetailsServiceMock);

    }
}
