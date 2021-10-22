package com.example.TravelApp.Unit;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeAccommodationDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Mapper.AccommodationDetailsMapper;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Repository.AccomodationDetailsRepository;
import com.example.TravelApp.Service.Implementations.AccommodationDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.*;
import javax.transaction.Transactional;
import static com.example.TravelApp.Unit.Constants.AccommodationConstants.*;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccommodationDetailsTests {

    @Mock
    private AccomodationDetailsRepository accommodationDetailsRepositoryMock;
    @Mock
    private AccommodationDetails accommodationDetailsMock;
    @Mock
    private AccommodationDetails accommodationDetailsMock2;
    @Mock
    private AccommodationDetailsDTO accommodationDetailsDTOMock;
    @InjectMocks
    private AccommodationDetailsMapper accommodationDetailsMapperMock;

    @Mock
    private AccommodationDetailsMapper accommodationDetailsMapper;

    @InjectMocks
    private AccommodationDetailsServiceImpl accommodationDetailsServiceMock;

    @Mock
    private AccommodationDetailsServiceImpl accommodationDetailsService;

    @Test
    public void testShouldFindByIdAccommodation() {
        // 1. Definisanje ponašanja
        when(accommodationDetailsRepositoryMock.findById(ID)).thenReturn(Optional.of(ACCOMMODATION_DETAILS));

        // 2. Akcija
        AccommodationDetails dbAccommodationDetails = accommodationDetailsRepositoryMock.findById(ID).get();
        // 3. Verifikacija
        assertEquals(ACCOMMODATION_DETAILS, dbAccommodationDetails);
        verify(accommodationDetailsRepositoryMock, times(1)).findById(ID);
        verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }

    @Test
    public void testShouldNotFindByIdAccommodation() {
        // 1. Definisanje ponašanja
        when(accommodationDetailsRepositoryMock.findById(ID)).thenReturn(Optional.of(ACCOMMODATION_DETAILS));
        when(accommodationDetailsRepositoryMock.findById(ID2)).thenReturn(Optional.of(ACCOMMODATION_DETAILS2));
        // 2. Akcija
        AccommodationDetails dbAccommodationDetails = accommodationDetailsRepositoryMock.findById(ID2).get();
        // 3. Verifikacija
        assertNotEquals(accommodationDetailsMapperMock.accommodationDetailsToDTO(ACCOMMODATION_DETAILS), dbAccommodationDetails);
        verify(accommodationDetailsRepositoryMock, times(1)).findById(ID2);
        verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }

    @Test
    public void testShouldFindByIdNoDTOAccommodation() {
        // 1. Definisanje ponašanja
        when(accommodationDetailsRepositoryMock.findById(ID)).thenReturn(Optional.of(accommodationDetailsMock));

        // 2. Akcija
        AccommodationDetails dbAccommodationDetails = accommodationDetailsServiceMock.findByIdNoDTO(ID);

        // 3. Verifikacija
        assertEquals(accommodationDetailsMock, dbAccommodationDetails);
        verify(accommodationDetailsRepositoryMock, times(1)).findById(ID);
        verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }

    @Test
    public void testShouldNotFindByIdNoDTOAccommodation() {
        // 1. Definisanje ponašanja
        when(accommodationDetailsRepositoryMock.findById(ID)).thenReturn(Optional.of(accommodationDetailsMock));
        when(accommodationDetailsRepositoryMock.findById(ID2)).thenReturn(Optional.of(accommodationDetailsMock2));

        // 2. Akcija
        AccommodationDetails dbAccommodationDetails = accommodationDetailsServiceMock.findByIdNoDTO(ID2);

        // 3. Verifikacija
        assertNotEquals(accommodationDetailsMock, dbAccommodationDetails);
        verify(accommodationDetailsRepositoryMock, times(1)).findById(ID2);
        verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }

    @Test
    public void testShouldFindByReqNoAccommodation() {
        // 1. Definisanje ponašanja
        Set<AccommodationDetails> accommodationDetailsSet = new HashSet<>();
        accommodationDetailsSet.add(ACCOMMODATION_DETAILS);
        when(accommodationDetailsRepositoryMock.findByAccommodationRequestNo(ACCOUNT_REQUEST_NUMBER)).thenReturn(accommodationDetailsSet);

        // 2. Akcija
        Set<AccommodationDetails> dbAccommodationDetails = accommodationDetailsServiceMock.findByAccommodationRequestNo(ACCOUNT_REQUEST_NUMBER);

        // 3. Verifikacija
        assertEquals(accommodationDetailsSet, dbAccommodationDetails);
        verify(accommodationDetailsRepositoryMock, times(1)).findByAccommodationRequestNo(ACCOUNT_REQUEST_NUMBER);
       // verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }

    @Test
    public void testShouldNotFindByReqNoAccommodation() {
        // 1. Definisanje ponašanja
        Set<AccommodationDetails> accommodationDetailsSet = new HashSet<>();
        accommodationDetailsSet.add(ACCOMMODATION_DETAILS);
        when(accommodationDetailsRepositoryMock.findByAccommodationRequestNo(ACCOUNT_REQUEST_NUMBER)).thenReturn(accommodationDetailsSet);

        // 2. Akcija
        Set<AccommodationDetails> dbAccommodationDetails = accommodationDetailsServiceMock.findByAccommodationRequestNo(ACCOUNT_REQUEST_NUMBER2);

        // 3. Verifikacija
        assertNotEquals(accommodationDetailsSet, dbAccommodationDetails);
        verify(accommodationDetailsRepositoryMock, times(1)).findByAccommodationRequestNo(ACCOUNT_REQUEST_NUMBER2);
        // verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }

    @Test
    public void testShouldFindAllAccommodation() {
        // 1. Definisanje ponašanja
        List<AccommodationDetails> accommodationDetailsSet = new ArrayList<>(getAccommodationDetailsSet());
        when(accommodationDetailsRepositoryMock.findAll()).thenReturn(accommodationDetailsSet);

        // 2. Akcija
        List<AccommodationDetails> dbAccommodationDetails = accommodationDetailsRepositoryMock.findAll();


        // 3. Verifikacija
        assertEquals(dbAccommodationDetails.size(),1);
        assertEquals(accommodationDetailsSet, dbAccommodationDetails);
        verify(accommodationDetailsRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testShouldSaveAccommodation() {
        // 1.Ponasanje
        List<AccommodationDetails> accommodationDetailsSet2 = new ArrayList<>(getAccommodationDetailsSet2());
        when(accommodationDetailsRepositoryMock.findAll()).thenReturn(accommodationDetailsSet2);
        when(accommodationDetailsRepositoryMock.save(ACCOMMODATION_DETAILS)).thenReturn(ACCOMMODATION_DETAILS);


        // 2. Akcija
        int dbSizeBeforeAdd = accommodationDetailsRepositoryMock.findAll().size();
        AccommodationDetails dbAccommodationDetails = accommodationDetailsRepositoryMock.save(ACCOMMODATION_DETAILS);
        when(accommodationDetailsServiceMock.findAll()).thenReturn(getAccommodationDetailsDTOSet());

        //3.Verifikacija
        assertThat(dbAccommodationDetails).isNotNull();
        Set<AccommodationDetailsDTO> accommodationDetailsDTOS = accommodationDetailsServiceMock.findAll();
        assertThat(accommodationDetailsDTOS).hasSize(dbSizeBeforeAdd + 1);
        verify(accommodationDetailsRepositoryMock, times(3)).findAll();
        verify(accommodationDetailsRepositoryMock, times(1)).save(ACCOMMODATION_DETAILS);

    }


    @Test
    @Transactional
    @Rollback(true)
    public void testShouldUpdateAccommodation() {
        // 1. Definisanje ponašanja

        when(accommodationDetailsRepositoryMock.findById(ID)).thenReturn(Optional.of(new AccommodationDetails(ID,ACCOUNT_NAME,EMPLOYEE,CHECK_OUT_DATE, CHECK_IN_DATE,ACCOUNT_REQUEST_NUMBER, BOOKING_PURPOSE, BILLING_DETAILS,PREVIOUS_ID, UPDATE_DATE, UPDATE_TIME, TRAVEL_DETAILS)));

        // 2. Akcija
        AccommodationDetails accommodationDetailsForUpdate = accommodationDetailsRepositoryMock.findById(ID).get();
        accommodationDetailsForUpdate.setBookingPurpose(NEW_BOOKING_PURPOSE);
        accommodationDetailsForUpdate.setBillingDetails(NEW_BILLING_DETAILS);

        when(accommodationDetailsRepositoryMock.save(accommodationDetailsForUpdate)).thenReturn(accommodationDetailsForUpdate);
        accommodationDetailsForUpdate = accommodationDetailsRepositoryMock.save(accommodationDetailsForUpdate);

        // 3. Verifikacija
        assertThat(accommodationDetailsForUpdate).isNotNull();
        accommodationDetailsForUpdate = accommodationDetailsRepositoryMock.findById(ID).get();
        assertThat(accommodationDetailsForUpdate.getBillingDetails()).isEqualTo(NEW_BILLING_DETAILS);
        assertThat(accommodationDetailsForUpdate.getBookingPurpose()).isEqualTo(NEW_BOOKING_PURPOSE);
        verify(accommodationDetailsRepositoryMock, times(2)).findById(ID);
        verify(accommodationDetailsRepositoryMock, times(1)).save(accommodationDetailsForUpdate);
        verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }


    //OVO
    @Test(expected = NoSuchElementException.class)
    public void testShouldNotUpdateAccommodation() {
        // 1. Definisanje ponašanja

        when(accommodationDetailsRepositoryMock.findById(ID)).thenReturn(Optional.of(new AccommodationDetails(ID,ACCOUNT_NAME,EMPLOYEE,CHECK_OUT_DATE, CHECK_IN_DATE,ACCOUNT_REQUEST_NUMBER, BOOKING_PURPOSE, BILLING_DETAILS,PREVIOUS_ID, UPDATE_DATE, UPDATE_TIME, TRAVEL_DETAILS)));

        // 2. Akcija
        AccommodationDetails accommodationDetailsForUpdate = accommodationDetailsRepositoryMock.findById(ID2).get();

        accommodationDetailsForUpdate.setBookingPurpose(NEW_BOOKING_PURPOSE);
        accommodationDetailsForUpdate.setBillingDetails(NEW_BILLING_DETAILS);

        when(accommodationDetailsRepositoryMock.save(accommodationDetailsForUpdate)).thenReturn(accommodationDetailsForUpdate);
        accommodationDetailsForUpdate = accommodationDetailsRepositoryMock.save(accommodationDetailsForUpdate);

        // 3. Verifikacija
        assertThat(accommodationDetailsForUpdate).isNotNull();
        accommodationDetailsForUpdate = accommodationDetailsRepositoryMock.findById(ID).get();
        assertThat(accommodationDetailsForUpdate.getBillingDetails()).isEqualTo(NEW_BILLING_DETAILS);
        assertThat(accommodationDetailsForUpdate.getBookingPurpose()).isEqualTo(NEW_BOOKING_PURPOSE);
        verify(accommodationDetailsRepositoryMock, times(2)).findById(ID);
        verify(accommodationDetailsRepositoryMock, times(1)).save(accommodationDetailsForUpdate);
        verifyNoMoreInteractions(accommodationDetailsRepositoryMock);
    }

    @Test
    public void testShouldParseAccommodationDetails() {
        // 1. Definisanje ponašanja
        List<JsonEmployeeAccommodationDetail> jsonEmployeeAccommodationDetails = new ArrayList<>();
        jsonEmployeeAccommodationDetails.add(JSON_EMPLOYEE_ACCOMMODATION_DETAIL);

        // 2. Akcija
        accommodationDetailsService.parseAccommodationDetails(jsonEmployeeAccommodationDetails);

        // 3. Verifikacija
        verify(accommodationDetailsService, times(1)).parseAccommodationDetails(jsonEmployeeAccommodationDetails);
        verifyNoMoreInteractions(accommodationDetailsService);
    }

    @Test
    public void testShouldReturnThatAccommodationAndTravelDetailsAreCompatible(){
        // 1. Definisanje ponašanja
        AccommodationDetailsDTO accommodationDetailsDTO = getAccommodationDetailsDTO();
        TravelDetailsDTO travelDetailsDTO = getTravelDetailsDTOForCompatibility();

        // 2. Akcija
        String accommodationRequestNo = accommodationDetailsServiceMock.accommodationAndTravelCompatibility(accommodationDetailsDTO, travelDetailsDTO);

        // 3. Verifikacija
        assertEquals(accommodationRequestNo, accommodationDetailsDTO.getAccommodationRequestNo());
    }

    @Test
    public void testShouldReturnThatAccommodationAndTravelDetailsAreNotCompatible(){
        // 1. Definisanje ponašanja
        AccommodationDetailsDTO accommodationDetailsDTO = getAccommodationDetailsDTO2();
        TravelDetailsDTO travelDetailsDTO = getTravelDetailsDTOForCompatibility();

        // 2. Akcija
        String accommodationRequestNo = accommodationDetailsServiceMock.accommodationAndTravelCompatibility(accommodationDetailsDTO, travelDetailsDTO);

        // 3. Verifikacija
        assertEquals(accommodationRequestNo, null);
    }
}
