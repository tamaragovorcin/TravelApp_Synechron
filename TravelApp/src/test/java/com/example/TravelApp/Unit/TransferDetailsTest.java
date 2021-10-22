package com.example.TravelApp.Unit;


import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.DTOs.TransferDetailsReserveDTO;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Mapper.EmailMapper;
import com.example.TravelApp.Mapper.TransferDetailsMapper;
import com.example.TravelApp.Mapper.TravelDetailsMapper;
import com.example.TravelApp.Model.TransferDetails;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Repository.TransferDetailsRepository;
import com.example.TravelApp.Repository.TravelDetailsRepository;
import com.example.TravelApp.Service.Implementations.TransferDetailsServiceImpl;
import com.example.TravelApp.Service.Implementations.TransferInformationServiceImpl;
import com.example.TravelApp.Service.Implementations.TravelDetailsServiceImpl;
import com.example.TravelApp.Service.Implementations.TravelInformationServiceImpl;
import com.example.TravelApp.Service.Interfaces.ITransferDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static com.example.TravelApp.Unit.Constants.TransferDetailsConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransferDetailsTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private TransferDetailsRepository transferDetailsRepositoryMock;

    @Mock
    private TransferDetails transferDetailsMock;

    @InjectMocks
    private TransferDetailsServiceImpl transferDetailsServiceMock;

    @InjectMocks
    private TransferInformationServiceImpl transferInformationServiceMock;

    @Mock
    private TransferDetailsMapper transferDetailsMapper;

    @Mock
    private EmailMapper emailMapper;

    @Test
    public void testShouldFindTransferByTransferReqId(){
        Set<TransferDetails> transferDetailsSet = getTransferDetails();
        when(transferDetailsRepositoryMock.findByTransferReqID(TRAVEL_REQ_ID)).thenReturn(transferDetailsSet);

        Set<TransferDetails> transferDetails = transferDetailsServiceMock.findByTransferReqID(TRAVEL_REQ_ID);

        assertEquals(transferDetailsSet, transferDetails);
        verify(transferDetailsRepositoryMock, times(1)).findByTransferReqID(TRAVEL_REQ_ID);
        verifyNoMoreInteractions(transferDetailsRepositoryMock);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldNotFindTransferByTransferReqId(){
        when(transferDetailsRepositoryMock.findByTransferReqID("12345")).thenThrow(new NullPointerException("Error"));

        Set<TransferDetails> transferDetailsDb = transferDetailsServiceMock.findByTransferReqID("12345");
        assertEquals(transferDetailsDb, new HashSet<TravelDetails>());
    }

    @Test
    public void testShouldFindAllTransferDetails() {

        List<TransferDetails> transferDetailsList = new ArrayList<>(getTransferDetailsList());
        when(transferDetailsRepositoryMock.findAll()).thenReturn(transferDetailsList);

        List<TransferDetails> travelDetailsDb = transferDetailsRepositoryMock.findAll();

        assertEquals(travelDetailsDb.size(),2);
        assertEquals(transferDetailsList, travelDetailsDb);
        verify(transferDetailsRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(transferDetailsRepositoryMock);
    }

    @Test
    public void testShouldFindTransferDetailsBetweenDates() {
        List<TransferDetails> transferDetailsList = new ArrayList<>(getTransferDetailsList());
        LocalDate localDate1 = LocalDate.of(2019,8,1);
        LocalDate localDate2 = LocalDate.of(2023,8,1);

        when(transferDetailsRepositoryMock.findAllTransferDetailsBetweenDates(localDate1,localDate2)).thenReturn(transferDetailsList);

        List<TransferDetails> transferDetailsDb = transferInformationServiceMock.getTransferInfoWithDates("2019-08-01","2023-08-01");

        assertEquals(transferDetailsList, transferDetailsDb);
        verify(transferDetailsRepositoryMock, times(1)).findAllTransferDetailsBetweenDates(localDate1,localDate2);
        verifyNoMoreInteractions(transferDetailsRepositoryMock);
    }

    @Test
    public void testShouldNotFindTransferDetailsBetweenDates() {
        List<TransferDetails> transferDetailsList = new ArrayList<>();
        LocalDate localDate1 = LocalDate.of(2027,8,1);
        LocalDate localDate2 = LocalDate.of(2035,8,1);

        when(transferDetailsRepositoryMock.findAllTransferDetailsBetweenDates(localDate1,localDate2)).thenReturn(transferDetailsList);

        List<TransferDetails> transferDetailsDb = transferInformationServiceMock.getTransferInfoWithDates("2027-08-01","2035-08-01");

        assertEquals(transferDetailsDb, transferDetailsList);
    }

    @Test
    public void testShouldReserveTransfer(){
        Set<TransferDetailsDTO> transferDetailsSetDTOS = getOneReservedTransferDetailsDTO();
        List<TransferDetailsDTO> transferDetailsDTOS = new ArrayList<>();
        for(TransferDetailsDTO transferDetailsDTO : transferDetailsSetDTOS){
            transferDetailsDTOS.add(transferDetailsDTO);
        }
        TransferDetails transferDetails = getOneTransferDetails();

        when(transferDetailsRepositoryMock.save(transferDetails)).thenReturn(transferDetails);
        when(transferDetailsRepositoryMock.findById(transferDetails.getId())).thenReturn(Optional.of(transferDetails));
        when(transferDetailsMapper.transferDetailsToDTO(transferDetails)).thenReturn(transferDetailsDTOS.get(0));
        when(emailMapper.emailDTOFromTransferDTO(transferDetailsDTOS.get(0))).thenReturn(getEmailDTO());
        Set<TransferDetailsReserveDTO> transferDetailsReserveDTOS = getTransferDetailsReserveDTO();
        Set<TransferDetailsDTO> reservedTransfers = transferInformationServiceMock.reserveTransfer(transferDetailsReserveDTOS);

        assertEquals(reservedTransfers, transferDetailsSetDTOS);
    }

    @Test
    public void testShouldSearchTransferDetails(){

    }

}
