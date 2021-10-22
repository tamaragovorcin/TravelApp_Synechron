package com.example.TravelApp.Unit;

import com.example.TravelApp.DTOs.EmailDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.ExelFiles.FileCreators.DecisionForABusinessTripCreator;
import com.example.TravelApp.Mapper.DecisionForABusinessTripMapper;
import com.example.TravelApp.Mapper.EmailMapper;
import com.example.TravelApp.Mapper.EmployeeMapper;
import com.example.TravelApp.Mapper.TravelDetailsMapper;
import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Repository.DecisionForABusinessTripRepository;
import com.example.TravelApp.Service.Implementations.DecisionForABusinessTripServiceImpl;
import com.example.TravelApp.Service.Implementations.DecisionTripInfoServiceImpl;
import com.example.TravelApp.Service.Implementations.EmailServiceImpl;
import com.example.TravelApp.Unit.Constants.DecisionForABusinessTripConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.example.TravelApp.Unit.Constants.DecisionForABusinessTripConstants.*;
import static com.example.TravelApp.Unit.Constants.TravelDetailsConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DecisionForABusinessTripTests {
    @Mock
    private DecisionForABusinessTripRepository decisionForABusinessTripRepositoryMock;

    @Mock
    private DecisionForABusinessTrip decisionForABusinessTripMock;

    @InjectMocks
    private DecisionForABusinessTripServiceImpl decisionForABusinessTripServiceMock;

    @Mock
    private DecisionForABusinessTripServiceImpl decisionForABusinessTripServiceMockNoInject;

    @Mock
    private DecisionForABusinessTripMapper decisionForABusinessTripMapperMock;

    @Mock
    private EmployeeMapper employeeMapperMock;

    @Mock
    private static TravelDetailsMapper travelDetailsMapperMock;

    @Mock
    private DecisionTripInfoServiceImpl decisionTripInfoServiceMock;

    @Mock
    private DecisionForABusinessTripCreator decisionForABusinessTripCreator;

    @Mock
    private EmailServiceImpl emailServiceMock;

    @Mock
    private EmailMapper emailMapperMock;

    @Mock
    private EmailDTO emailDTOMock;

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void testShouldFindDecisionByTravelReqId() {

        DecisionForABusinessTrip decisionForABusinessTrip = getDecisionForBusinessTrip();
        String travel_req_id = DecisionForABusinessTripConstants.TRAVEL_REQ_ID;
        when(decisionForABusinessTripRepositoryMock.findByTravelReqID(travel_req_id)).thenReturn(decisionForABusinessTrip);

        DecisionForABusinessTrip decisionForABusinessTripDb = decisionForABusinessTripServiceMock.findByTravelReqID(travel_req_id);

        assertEquals(decisionForABusinessTrip, decisionForABusinessTripDb);
        verify(decisionForABusinessTripRepositoryMock, times(1)).findByTravelReqID(travel_req_id);
        verifyNoMoreInteractions(decisionForABusinessTripRepositoryMock);
    }

    @Test
    public void testShouldFindAllDecisionsForABusinessTrip() {

        List<DecisionForABusinessTrip> decisionForABusinessTripsList = new ArrayList<>(getDecisionsForABusinessTripList());
        when(decisionForABusinessTripRepositoryMock.findAll()).thenReturn(decisionForABusinessTripsList);

        List<DecisionForABusinessTrip> decisionForABusinessTripsDb = decisionForABusinessTripRepositoryMock.findAll();

        assertEquals(decisionForABusinessTripsList, decisionForABusinessTripsDb);
        verify(decisionForABusinessTripRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(decisionForABusinessTripRepositoryMock);
    }

    @Test
    public void testShouldCreateDecisionsForABusinessTrip() {
        List<TravelDetailsDTO> travelDetailsDTOList = new ArrayList<>(getTravelDetailsDTOForDecision());
        List<DecisionForABusinessTrip> decisionForABusinessTrips = getDecisionsForABusinessTripList();
        DecisionForABusinessTrip decision = getDecisionForBusinessTrip();
        when(decisionForABusinessTripMapperMock.travelDetailsDTOtoDecision(travelDetailsDTOList)).thenReturn(decision);
        when(decisionForABusinessTripRepositoryMock.save(decision)).thenReturn(decision);
        when(decisionForABusinessTripRepositoryMock.findAll()).thenReturn(getDecisionsForABusinessTripList());
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setFilepath("");
        emailDTO.setTravelReqId("3432");

        when(emailMapperMock.emailDtoFromFilepath(null)).thenReturn(emailDTO);
        when(emailMapperMock.emailDtoFromFilepathAndTravelReqId(null,"3432")).thenReturn(emailDTO);

        int dbSizeBeforeCreate = decisionForABusinessTripRepositoryMock.findAll().size();
        DecisionForABusinessTrip decisionForABusinessTripCreated = decisionForABusinessTripServiceMock.save(travelDetailsDTOList);

        decisionForABusinessTrips.add(decisionForABusinessTripCreated);
        when(decisionForABusinessTripRepositoryMock.findAll()).thenReturn(decisionForABusinessTrips);

        assertEquals(decisionForABusinessTripRepositoryMock.findAll().size(),dbSizeBeforeCreate + 1);
        assertNotNull(decisionForABusinessTripCreated);
    }

    public static List<TravelDetailsDTO> getTravelDetailsDTOForDecision() {
        List<TravelDetailsDTO> travelDetailsDTOS = new ArrayList<>();
        TravelDetailsDTO travelDetailsDTO = travelDetailsMapperMock.travelDetailsToDTO(getTravelDetailsForCreate());
        travelDetailsDTOS.add(travelDetailsDTO);
        travelDetailsDTOS.add(travelDetailsDTO);
        return travelDetailsDTOS;
    }

    @Test
    public void testShouldFindMaxDecisionsNumber() {

        List<DecisionForABusinessTrip> decisionForABusinessTripsList = new ArrayList<>(getDecisionsForABusinessTripList());
        when(decisionForABusinessTripServiceMockNoInject.getMaxNumber("2021",decisionForABusinessTripsList)).thenReturn(2L);

        long decisionNumber = decisionForABusinessTripServiceMockNoInject.getMaxNumber("2021",decisionForABusinessTripsList);

        assertEquals(decisionNumber, 2L);
        verify(decisionForABusinessTripServiceMockNoInject, times(1)).getMaxNumber("2021",decisionForABusinessTripsList);
        verifyNoMoreInteractions(decisionForABusinessTripServiceMockNoInject);
    }

    @Test
    public void testShouldFindNextDecisionNumber() {

        DecisionForABusinessTrip decisionForABusinessTrip = getDecisionForBusinessTrip();
        when(decisionForABusinessTripRepositoryMock.save(decisionForABusinessTrip)).thenReturn(decisionForABusinessTrip);
        when(decisionForABusinessTripServiceMockNoInject.findNextDecisionNumber()).thenReturn("2_2021");

        decisionForABusinessTripRepositoryMock.save(decisionForABusinessTrip);

        String decisionNumber = decisionForABusinessTripServiceMockNoInject.findNextDecisionNumber();

        assertEquals(decisionNumber, "2_2021");
        verify(decisionForABusinessTripServiceMockNoInject, times(1)).findNextDecisionNumber();
        verifyNoMoreInteractions(decisionForABusinessTripServiceMockNoInject);
    }

    @Test
    public void testShouldGetFileAsByteArray() {
        String filepath = "src/test/java/com/example/TravelApp/Unit/test.xlsx";
        when(decisionForABusinessTripServiceMockNoInject.getFileByteArr(filepath)).thenReturn(filepath.getBytes());

        byte[] bytes = decisionForABusinessTripServiceMockNoInject.getFileByteArr(filepath);

        assertEquals(Arrays.toString(filepath.getBytes()),Arrays.toString(bytes));
    }

    @Test(expected = NullPointerException.class)
    public void testShouldNotGetFileAsByteArray() {
        String filepath = "src/test/java/com/example/TravelApp/Unit/test2.xlsx";
        when(decisionForABusinessTripServiceMockNoInject.getFileByteArr(filepath)).thenThrow(new NullPointerException("Error"));

        byte[] bytes = decisionForABusinessTripServiceMockNoInject.getFileByteArr(filepath);

        assertEquals(Arrays.toString(filepath.getBytes()),Arrays.toString(bytes));
    }

    @Test
    public void testShouldCreateCalculation() {
        String filepath = "src/test/java/com/example/TravelApp/Unit/test.xlsx";
        DecisionForABusinessTrip decisionForABusinessTrip = getDecisionForBusinessTrip();

        when(decisionForABusinessTripServiceMockNoInject.createCalculation(decisionForABusinessTrip,filepath)).thenReturn(filepath.getBytes());

        byte[] bytes = decisionForABusinessTripServiceMockNoInject.createCalculation(decisionForABusinessTrip,filepath);

        assertEquals(Arrays.toString(filepath.getBytes()),Arrays.toString(bytes));
    }
}
