package com.example.TravelApp.Unit;

import com.example.TravelApp.Controllers.TravelDetailsController;
import com.example.TravelApp.Controllers.TravelInformationController;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static com.example.TravelApp.Unit.Constants.TravelDetailsConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelDetailsControllerTest {

    @Mock
    private TravelDetailsRepository travelDetailsRepositoryMock;

    @Mock
    private TravelDetails travelDetailsMock;

    @Mock
    private TravelDetailsServiceImpl travelDetailsServiceMock;

    @InjectMocks
    private TravelDetailsController travelDetailsControllerMock;

    @InjectMocks
    private TravelInformationController travelInformationControllerMock;

    @Mock
    private TravelInformationServiceImpl travelInformationServiceMock;

    @Mock
    private TravelDetailsMapper travelDetailsMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testShouldFindAllTravelDetails()  {

        List<TravelDetails> travelDetailsList = new ArrayList<>(getTravelDetailsSet());
        Set<TravelDetailsDTO> travelDetailsDTOS = travelDetailsMapper.getTravelDetailsDTOS(travelDetailsList);
        when(travelDetailsServiceMock.findAll()).thenReturn(travelDetailsDTOS);

        ResponseEntity<Set<TravelDetailsDTO>> result =  travelDetailsControllerMock.findAll();
        System.out.println("Body: "+result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(),travelDetailsDTOS);

    }

    @Test
    public void testShouldFindByIdTravelDetails() {

        TravelDetailsDTO travelDetailsDTO = travelDetailsMapper.travelDetailsToDTO(getTravelDetails());
        when(travelDetailsServiceMock.findById(ID)).thenReturn(travelDetailsDTO);

        ResponseEntity<TravelDetailsDTO> result =  travelDetailsControllerMock.findById(ID);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(),travelDetailsDTO);

    }
    @Test
    public void testShouldDeleteTravelDetails() {

        TravelDetailsDTO travelDetailsDTO = travelDetailsMapper.travelDetailsToDTO(getTravelDetails());
        when(travelDetailsServiceMock.findById(ID)).thenReturn(travelDetailsDTO);

        ResponseEntity<Long> result =  travelDetailsControllerMock.delete(ID);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(travelDetailsServiceMock,times(1)).delete(ID);
    }

    @Test
    public void testShouldCreateTravelDetails() {

        List<TravelDetails> travelDetailsList = new ArrayList<>(getTravelDetailsSet());
        Set<TravelDetailsDTO> travelDetailsDTOList = travelDetailsMapper.getTravelDetailsDTOS(travelDetailsList);

        TravelDetails travelDetails = getTravelDetailsForCreate();

        TravelDetailsDTO travelDetailsDTO = travelDetailsMapper.travelDetailsToDTO(travelDetails);
        when(travelDetailsServiceMock.findAll()).thenReturn(travelDetailsDTOList);
        when(travelDetailsServiceMock.save(travelDetailsDTO)).thenReturn(travelDetailsDTO);

        int dbSizeBeforeCreate = travelDetailsServiceMock.findAll().size();
        ResponseEntity<TravelDetailsDTO> result = travelDetailsControllerMock.create(travelDetailsDTO);

        travelDetailsList.add(travelDetailsMapper.travelDetailsDTOToTravelDetails(result.getBody()));
        travelDetailsDTOList.add(result.getBody());
        when(travelDetailsServiceMock.findAll()).thenReturn(travelDetailsDTOList);

        assertEquals(travelDetailsServiceMock.findAll().size(),dbSizeBeforeCreate + 1);

        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        verify(travelDetailsServiceMock, times(2)).findAll();
        verify(travelDetailsServiceMock, times(1)).save(travelDetailsDTO);

    }

    @Test
    public void testShouldFindTravelDetailsBetweenDates() {
        List<TravelDetails> travelDetailsList = new ArrayList<>(getTravelDetailsSet());
        Set<TravelDetailsDTO> travelDetailsDTOList = travelDetailsMapper.getTravelDetailsDTOS(travelDetailsList);


        when(travelInformationServiceMock.findBasicTravelInformation("2019-08-01","2023-08-01")).thenReturn(travelDetailsDTOList);

        ResponseEntity<Set<TravelDetailsDTO>> result = travelInformationControllerMock.findBasicTravelInformation("2019-08-01","2023-08-01");

        assertEquals(travelDetailsDTOList, result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.OK);

        verify(travelInformationServiceMock, times(1)).findBasicTravelInformation("2019-08-01","2023-08-01");
        verifyNoMoreInteractions(travelInformationServiceMock);
    }

}
