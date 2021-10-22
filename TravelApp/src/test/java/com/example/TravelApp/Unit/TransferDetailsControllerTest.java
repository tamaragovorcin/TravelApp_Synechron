package com.example.TravelApp.Unit;

import com.example.TravelApp.Controllers.TransferDetailsController;
import com.example.TravelApp.Controllers.TransferInformationController;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TransferDetailsReserveDTO;
import com.example.TravelApp.DTOs.TransferSearchDTO;
import com.example.TravelApp.Mapper.TransferDetailsMapper;
import com.example.TravelApp.Model.TransferDetails;
import com.example.TravelApp.Service.Implementations.TransferDetailsServiceImpl;
import com.example.TravelApp.Service.Implementations.TransferInformationServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.TravelApp.Unit.Constants.TransferDetailsConstants.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransferDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransferDetails transferDetailsMock;

    @Mock
    private TransferDetailsMapper transferDetailsMapperMock;

    @Mock
    private TransferDetailsServiceImpl transferDetailsServiceMock;

    @InjectMocks
    private TransferDetailsController transferDetailsControllerMock;

    @InjectMocks
    private TransferInformationController transferInformationControllerMock;

    @Mock
    private TransferInformationServiceImpl transferInformationServiceMock;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void testShouldFindAllTransferDetails() {
        List<TransferDetails> transferDetailsList = new ArrayList<>(getTransferDetailsList());
        List<TransferDetailsDTO> transferDetailsDTOS = new ArrayList<>();
        for(TransferDetails transferDetails : transferDetailsList){
            transferDetailsDTOS.add(transferDetailsMapperMock.transferDetailsToDTO(transferDetails));
        }
        when(transferDetailsServiceMock.findAll()).thenReturn(transferDetailsDTOS);
        ResponseEntity<List<TransferDetailsDTO>> result =  transferDetailsControllerMock.findAll();
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(),transferDetailsDTOS);

    }

    @Test
    public void testShouldFindTransferDetailsBetweenDates(){
        List<TransferDetails> transferDetailsList = new ArrayList<>(getTransferDetailsList());
        Set<TransferDetailsDTO> transferDetailsDTOS = new HashSet<TransferDetailsDTO>();
        for(TransferDetails transferDetails : transferDetailsList){
            transferDetailsDTOS.add(transferDetailsMapperMock.transferDetailsToDTO(transferDetails));
        }

        when(transferInformationServiceMock.findBasicTransferInformation("2019-08-01","2023-08-01")).thenReturn(transferDetailsDTOS);

        ResponseEntity<Set<TransferDetailsDTO>> result = transferInformationControllerMock.findBasicTravelInformation("2019-08-01","2023-08-01");
        //System.out.println("Body: "+result.getBody());
        //System.out.println("transferDetailsDTOS: "+transferDetailsDTOS);
        assertEquals(transferDetailsDTOS, result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.OK);

        verify(transferInformationServiceMock, times(1)).findBasicTransferInformation("2019-08-01","2023-08-01");
        verifyNoMoreInteractions(transferInformationServiceMock);
    }

    @Test
    public void testShouldFindSearchedTransferDetails(){
        List<TransferDetails> transferDetailsList = new ArrayList<>(getReservedTransferDetailsList());
        Set<TransferDetailsDTO> transferDetailsDTOS = new HashSet<TransferDetailsDTO>();
        for(TransferDetails transferDetails : transferDetailsList){
            transferDetailsDTOS.add(transferDetailsMapperMock.transferDetailsToDTO(transferDetails));
        }
        TransferSearchDTO transferSearchDTO = new TransferSearchDTO();
        when(transferInformationServiceMock.searchTransfers(transferSearchDTO)).thenReturn(transferDetailsDTOS);
        ResponseEntity<Set<TransferDetailsDTO>> result = transferInformationControllerMock.searchTransfers(transferSearchDTO);

        assertEquals(transferDetailsDTOS, result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(transferInformationServiceMock, times(1)).searchTransfers(transferSearchDTO);

    }

    @Test
    public void testShouldReserveTransfer(){
        List<TransferDetails> transferDetailsList = new ArrayList<>(getReservedTransferDetailsList());
        Set<TransferDetailsDTO> transferDetailsDTOS = new HashSet<>();
        for(TransferDetails transferDetails : transferDetailsList){
            transferDetailsDTOS.add(transferDetailsMapperMock.transferDetailsToDTO(transferDetails));
        }
        TransferDetailsReserveDTO transferSearchDTO = new TransferDetailsReserveDTO();
        Set<TransferDetailsReserveDTO> transferDetailsReserveDTOS = new HashSet<>();
        transferDetailsReserveDTOS.add(transferSearchDTO);

        when(transferInformationServiceMock.reserveTransfer(transferDetailsReserveDTOS)).thenReturn(transferDetailsDTOS);
        ResponseEntity<Set<TransferDetailsDTO>> result = transferInformationControllerMock.reserveTransfer(transferDetailsReserveDTOS);

        assertEquals(transferDetailsDTOS, result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.OK);

        verify(transferInformationServiceMock, times(1)).reserveTransfer(transferDetailsReserveDTOS);
    }

    @Test
    public void testShouldUpdateTransfer(){
        TransferDetailsDTO transferDetailsDTO = new TransferDetailsDTO();
        transferDetailsDTO.setAdditionalComments("None");

        when(transferDetailsServiceMock.update(transferDetailsDTO)).thenReturn(transferDetailsDTO);
        ResponseEntity<TransferDetailsDTO> result = transferDetailsControllerMock.update(transferDetailsDTO);

        assertEquals(transferDetailsDTO, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(transferDetailsServiceMock, times(1)).update(transferDetailsDTO);

    }
}