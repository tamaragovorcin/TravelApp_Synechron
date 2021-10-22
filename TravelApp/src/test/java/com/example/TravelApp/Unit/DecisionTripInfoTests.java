package com.example.TravelApp.Unit;

import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Model.DecisionTripInfo;
import com.example.TravelApp.Repository.DecisionTripInfoRepository;
import com.example.TravelApp.Service.Implementations.DecisionTripInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static com.example.TravelApp.Unit.Constants.DecisionTripInfoConstants.*;
import static com.example.TravelApp.Unit.Constants.DecisionForABusinessTripConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DecisionTripInfoTests {
    @Mock
    private DecisionTripInfoRepository decisionTripInfoRepositoryMock;

    @Mock
    private DecisionTripInfo decisionTripInfoMock;

    @InjectMocks
    private DecisionTripInfoServiceImpl decisionTripInfoServiceMock;

    @Test
    public void testShouldCreateDecisionTripInfo() {
        List<DecisionTripInfo> decisionTripInfoList = getDecisionTripInfoList();
        DecisionTripInfo decisionTripInfo = getDecisionTripInfoForCreate();

        when(decisionTripInfoRepositoryMock.findAll()).thenReturn(decisionTripInfoList);
        when(decisionTripInfoRepositoryMock.save(decisionTripInfo)).thenReturn(decisionTripInfo);

        int dbSizeBeforeCreate = decisionTripInfoRepositoryMock.findAll().size();
        DecisionTripInfo decisionTripInfoCreated = decisionTripInfoRepositoryMock.save(decisionTripInfo);

        decisionTripInfoList.add(decisionTripInfoCreated);
        when(decisionTripInfoRepositoryMock.findAll()).thenReturn(decisionTripInfoList);

        assertEquals(decisionTripInfoRepositoryMock.findAll().size(),dbSizeBeforeCreate + 1);

        assertNotNull(decisionTripInfoCreated);
        verify(decisionTripInfoRepositoryMock, times(2)).findAll();
        verify(decisionTripInfoRepositoryMock, times(1)).save(decisionTripInfo);

    }

    @Test
    public void testShouldCreateAllDecisionTripInfo() {
        List<DecisionTripInfo> decisionTripInfoList = getDecisionTripInfoList();
        List<DecisionTripInfo> decisionTripInfoListForCreate = new ArrayList<>(getDecisionTripInfoListForCreate());

        when(decisionTripInfoRepositoryMock.findAll()).thenReturn(decisionTripInfoList);
        when(decisionTripInfoRepositoryMock.saveAll(decisionTripInfoListForCreate)).thenReturn(decisionTripInfoListForCreate);

        int dbSizeBeforeCreate = decisionTripInfoRepositoryMock.findAll().size();
        List<DecisionTripInfo> decisionTripInfoCreated = decisionTripInfoRepositoryMock.saveAll(decisionTripInfoListForCreate);

        decisionTripInfoList.addAll(decisionTripInfoCreated);

        when(decisionTripInfoRepositoryMock.findAll()).thenReturn(decisionTripInfoList);

        assertEquals(decisionTripInfoRepositoryMock.findAll().size(),dbSizeBeforeCreate + decisionTripInfoListForCreate.size());

        assertNotNull(decisionTripInfoCreated);
        verify(decisionTripInfoRepositoryMock, times(1)).saveAll(decisionTripInfoListForCreate);
    }

    @Test
    public void testShouldCreateAllDecisionTripInfoService() {
        Set<DecisionTripInfo> decisionTripInfoListForCreate = getDecisionTripInfoListForCreate();
        DecisionForABusinessTrip decisionForABusinessTrip = getDecisionForBusinessTrip();
        List<DecisionTripInfo> list = new ArrayList<>(decisionTripInfoListForCreate);
        when(decisionTripInfoServiceMock.save(decisionTripInfoMock)).thenReturn(decisionTripInfoMock);
        when(decisionTripInfoRepositoryMock.save(decisionTripInfoMock)).thenReturn(decisionTripInfoMock);

        List<DecisionTripInfo> decisionTripInfoCreated = decisionTripInfoServiceMock.saveAll(decisionTripInfoListForCreate,decisionForABusinessTrip);

        assertNotNull(decisionTripInfoCreated);
        assertEquals(decisionTripInfoCreated.size(),decisionTripInfoListForCreate.size());
    }

}
