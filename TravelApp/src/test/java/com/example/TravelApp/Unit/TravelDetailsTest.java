package com.example.TravelApp.Unit;

import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Mapper.TravelDetailsMapper;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Repository.TravelDetailsRepository;
import com.example.TravelApp.Service.Implementations.TravelDetailsServiceImpl;
import com.example.TravelApp.Service.Implementations.TravelInformationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.example.TravelApp.Unit.Constants.TravelDetailsConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelDetailsTest {

    @Mock
    private TravelDetailsRepository travelDetailsRepositoryMock;

    @Mock
    private TravelDetails travelDetailsMock;

    @InjectMocks
    private TravelDetailsServiceImpl travelDetailsServiceMock;

    @InjectMocks
    private TravelInformationServiceImpl travelInformationServiceMock;

    @Mock
    private TravelDetailsMapper travelDetailsMapper;


    @Test
    public void testShouldFindTravelDetailsById() {
        when(travelDetailsRepositoryMock.findById(ID)).thenReturn(Optional.of(travelDetailsMock));

        TravelDetailsDTO travelDetailsDb = travelDetailsServiceMock.findById(ID);

        assertEquals(travelDetailsMapper.travelDetailsToDTO(travelDetailsMock), travelDetailsDb);
        verify(travelDetailsRepositoryMock, times(1)).findById(ID);
        verifyNoMoreInteractions(travelDetailsRepositoryMock);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldNotFindTravelDetailsById() {
        TravelDetails travelDetails = getTravelDetails();
        when(travelDetailsRepositoryMock.save(travelDetails)).thenReturn(travelDetails);

        travelDetailsRepositoryMock.save(travelDetails);
        when(travelDetailsRepositoryMock.findById(123456L)).thenThrow(new NullPointerException("Error"));
        TravelDetailsDTO travelDetailsDb = travelDetailsServiceMock.findById(123456L);
        assertNull(travelDetailsDb);
    }

    @Test
    public void testShouldFindTravelDetailsByReqId() {

        Set<TravelDetails> travelDetailsSet = getTravelDetailsSet();
        when(travelDetailsRepositoryMock.findByTravelReqID(TRAVEL_REQ_ID)).thenReturn(travelDetailsSet);

        Set<TravelDetails> travelDetailsDb = travelDetailsServiceMock.findByTravelReqID(TRAVEL_REQ_ID);

        assertEquals(travelDetailsSet, travelDetailsDb);
        verify(travelDetailsRepositoryMock, times(1)).findByTravelReqID(TRAVEL_REQ_ID);
        verifyNoMoreInteractions(travelDetailsRepositoryMock);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldNotFindTravelDetailsByReqId() {

        when(travelDetailsRepositoryMock.findByTravelReqID("12345")).thenThrow(new NullPointerException("Error"));

        Set<TravelDetails> travelDetailsDb = travelDetailsServiceMock.findByTravelReqID("12345");
        assertEquals(travelDetailsDb, new HashSet<TravelDetails>());
    }

    @Test
    public void testShouldFindAllTravelDetails() {

        List<TravelDetails> travelDetailsList = new ArrayList<>(getTravelDetailsSet());
        when(travelDetailsRepositoryMock.findAll()).thenReturn(travelDetailsList);

        List<TravelDetails> travelDetailsDb = travelDetailsRepositoryMock.findAll();

        assertEquals(travelDetailsDb.size(),2);
        assertEquals(travelDetailsList, travelDetailsDb);
        verify(travelDetailsRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(travelDetailsRepositoryMock);
    }

    @Test
    public void testShouldFindTravelDetailsBetweenDates() {
        List<TravelDetails> travelDetailsList = new ArrayList<>(getTravelDetailsSet());
        LocalDate localDate1 = LocalDate.of(2019,8,1);
        LocalDate localDate2 = LocalDate.of(2023,8,1);

        when(travelDetailsRepositoryMock.findAllTravelDetailsBetweenDates(localDate1,localDate2)).thenReturn(travelDetailsList);

        List<TravelDetails> travelDetailsDb = travelInformationServiceMock.getTravelInfoWithDates("2019-08-01","2023-08-01");

        assertEquals(travelDetailsList, travelDetailsDb);
        verify(travelDetailsRepositoryMock, times(1)).findAllTravelDetailsBetweenDates(localDate1,localDate2);
        verifyNoMoreInteractions(travelDetailsRepositoryMock);
    }

    @Test
    public void testShouldNotFindTravelDetailsBetweenDates() {
        List<TravelDetails> travelDetailsList = new ArrayList<>(getTravelDetailsSet());
        LocalDate localDate1 = LocalDate.of(2027,8,1);
        LocalDate localDate2 = LocalDate.of(2035,8,1);

        when(travelDetailsRepositoryMock.findAllTravelDetailsBetweenDates(localDate1,localDate2)).thenReturn(travelDetailsList);

        List<TravelDetails> travelDetailsDb = travelInformationServiceMock.getTravelInfoWithDates("2019-08-01","2023-08-01");

        assertEquals(travelDetailsDb, new ArrayList<>());
    }

    @Test
    public void testShouldDeleteTravelDetails() {
        List<TravelDetails> travelDetailsList = new ArrayList<>(getTravelDetailsSet());

        travelDetailsRepositoryMock.delete(travelDetailsList.get(0));

        travelDetailsServiceMock.delete(travelDetailsList.get(0).getId());

        assertEquals(travelDetailsList.size()-2, travelDetailsServiceMock.findAll().size());

    }

    @Test(expected = NullPointerException.class)
    public void testShouldNotDeleteTravelDetails() {
        TravelDetails travelDetails = getTravelDetailsForCreate();

        when(travelDetailsRepositoryMock.save(travelDetails)).thenReturn(travelDetails);
        doThrow(new NullPointerException()).when(travelDetailsRepositoryMock).delete(travelDetailsMock);
        travelDetailsRepositoryMock.delete(travelDetailsMock);

    }

    @Test
    public void testShouldCreateTravelDetails() {
        List<TravelDetails> travelDetailsList = new ArrayList<>(getTravelDetailsSet());
        TravelDetails travelDetails = getTravelDetailsForCreate();

        when(travelDetailsRepositoryMock.findAll()).thenReturn(travelDetailsList);
        when(travelDetailsRepositoryMock.save(travelDetails)).thenReturn(travelDetails);

        int dbSizeBeforeCreate = travelDetailsRepositoryMock.findAll().size();
        TravelDetails travelDetailsCreated = travelDetailsRepositoryMock.save(travelDetails);

        travelDetailsList.add(travelDetailsCreated);
        when(travelDetailsRepositoryMock.findAll()).thenReturn(travelDetailsList);

        assertEquals(travelDetailsRepositoryMock.findAll().size(),dbSizeBeforeCreate + 1);

        assertNotNull(travelDetailsCreated);
        verify(travelDetailsRepositoryMock, times(2)).findAll();
        verify(travelDetailsRepositoryMock, times(1)).save(travelDetails);
    }

}
