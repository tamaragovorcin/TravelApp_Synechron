package com.example.TravelApp.Unit;

import com.example.TravelApp.Controllers.DecisionForABusinessTripController;
import com.example.TravelApp.Mapper.DecisionForABusinessTripMapper;
import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Repository.DecisionForABusinessTripRepository;
import com.example.TravelApp.Service.Implementations.DecisionForABusinessTripServiceImpl;
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
import static com.example.TravelApp.Unit.Constants.DecisionForABusinessTripConstants.getDecisionForBusinessTrip;
import static com.example.TravelApp.Unit.Constants.TravelDetailsConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DecisionForABusinessTripControllerTests {
    @Mock
    private DecisionForABusinessTripRepository decisionForABusinessTripRepository;

    @Mock
    private DecisionForABusinessTrip decisionForABusinessTripMock;

    @Mock
    private DecisionForABusinessTripServiceImpl decisionForABusinessTripServiceMock;

    @InjectMocks
    private DecisionForABusinessTripController decisionForABusinessTripControllerMock;

    @Mock
    private DecisionForABusinessTripMapper decisionForABusinessTripMapperMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShouldFindByIdTravelDetails() {
        DecisionForABusinessTrip decisionForABusinessTrip = getDecisionForBusinessTrip();

        when(decisionForABusinessTripServiceMock.findByTravelReqID(decisionForABusinessTrip.getTravelReqID())).thenReturn(decisionForABusinessTrip);

        ResponseEntity<DecisionForABusinessTrip> result =  decisionForABusinessTripControllerMock.getDecisionByTravelReqId(TRAVEL_REQ_ID);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(),decisionForABusinessTrip);

    }
}
