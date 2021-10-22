package com.example.TravelApp.Mapper;

import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeTravelDetail;
import com.example.TravelApp.DTOs.TransferDetailsDTO;
import com.example.TravelApp.Helpers.TimeAndDateParser;
import com.example.TravelApp.Model.TransferDetails;
import com.example.TravelApp.Model.TravelDetails;
import com.example.TravelApp.Service.Interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TransferDetailsMapper {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private AccommodationDetailsMapper accommodationDetailsMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    public TransferDetails jsonDetailsToTransferDetails(JsonEmployeeTravelDetail jsonEmployeeTravelDetail) {
        TransferDetails transferDetails = new TransferDetails();

        transferDetails.setTravelReqID(jsonEmployeeTravelDetail.getTravelReqID());
        transferDetails.setArrivalCountry(jsonEmployeeTravelDetail.getArrivalCountry());
        transferDetails.setArrivalDate(TimeAndDateParser.parseDate(jsonEmployeeTravelDetail.getArrivalDate()));
        transferDetails.setArrivalTime(TimeAndDateParser.parseTime(jsonEmployeeTravelDetail.getArrivalTime()));
        transferDetails.setArriveTo(jsonEmployeeTravelDetail.getArriveTo());
        transferDetails.setDepartFrom(jsonEmployeeTravelDetail.getDepartFrom());
        transferDetails.setDepartingCountry(jsonEmployeeTravelDetail.getDepartingCountry());
        transferDetails.setDepartureDate(TimeAndDateParser.parseDate(jsonEmployeeTravelDetail.getDepartureDate()));
        transferDetails.setDepartureTime(TimeAndDateParser.parseTime(jsonEmployeeTravelDetail.getDepartureTime()));
        transferDetails.setEmployee(employeeService.findByEmployeeId(jsonEmployeeTravelDetail.getEmployeeID()));
        return transferDetails;
    }

    public TransferDetailsDTO transferDetailsToDTO(TransferDetails transferDetails) {
        TransferDetailsDTO transferDetailsDTO = new TransferDetailsDTO();

        transferDetailsDTO.setId(transferDetails.getId());
        transferDetailsDTO.setTravelReqID(transferDetails.getTravelReqID());
        transferDetailsDTO.setFlightId(transferDetails.getFlightId());
        transferDetailsDTO.setDepartFrom(transferDetails.getDepartFrom());
        transferDetailsDTO.setArriveTo(transferDetails.getArriveTo());
        transferDetailsDTO.setDepartureDate(transferDetails.getDepartureDate().toString());
        transferDetailsDTO.setEmployee(employeeMapper.employeeToDTO(transferDetails.getEmployee()));
        transferDetailsDTO.setArrivalTime(transferDetails.getArrivalTime().toString());
        transferDetailsDTO.setArrivalDate(transferDetails.getArrivalDate().toString());
        transferDetailsDTO.setDepartureTime(transferDetails.getDepartureTime().toString());
        transferDetailsDTO.setDepartingCountry(transferDetails.getDepartingCountry());
        transferDetailsDTO.setArrivalCountry(transferDetails.getArrivalCountry());
        transferDetailsDTO.setAdditionalComments(transferDetails.getAdditionalComments());
        transferDetailsDTO.setPreviousId(transferDetails.getPreviousId());

        if(transferDetails.getUpdateDate() != null){
            transferDetailsDTO.setUpdateDate(transferDetails.getUpdateDate().toString());
        }else{
            transferDetailsDTO.setUpdateDate(null);
        }
        if(transferDetailsDTO.getUpdateTime() != null){
            transferDetailsDTO.setUpdateTime(transferDetailsDTO.getUpdateTime().toString());
        }else{
            transferDetailsDTO.setUpdateTime(null);
        }
        if(transferDetails.getReservationDate() != null){
            transferDetailsDTO.setReservationDate(transferDetails.getReservationDate().toString());
        }else{
            transferDetailsDTO.setReservationDate(null);
        }
        if(transferDetails.getReservationTime() !=null){
            transferDetailsDTO.setReservationTime(transferDetails.getReservationTime().toString());

        }else{
            transferDetailsDTO.setReservationTime(null);

        }
        if(transferDetails.getTransferDate() != null){
            transferDetailsDTO.setTransferDate(transferDetails.getTransferDate().toString());
        }
        else{
            transferDetailsDTO.setTransferDate(null);
        }
        if(transferDetails.getTransferTime() != null){
            transferDetailsDTO.setTransferTime(transferDetails.getTransferTime().toString());
        }
        else{
            transferDetailsDTO.setTransferTime(null);
        }
        if(transferDetails.getTransferCity() != null){
            transferDetailsDTO.setTransferCity(transferDetails.getTransferCity());
        }
        else{
            transferDetailsDTO.setTransferCity(null);
        }
        if(transferDetails.getTransferAddress() != null){
            transferDetailsDTO.setTransferAddress(transferDetails.getTransferAddress());
        }else{
            transferDetailsDTO.setTransferAddress(null);
        }

        return transferDetailsDTO;
    }

    public TransferDetails transferDetailsDTOToTransferDetails(TransferDetailsDTO transferDetailsDTO){
        TransferDetails transferDetails = new TransferDetails();

        transferDetails.setAdditionalComments(transferDetailsDTO.getAdditionalComments());
        transferDetails.setTravelReqID(transferDetailsDTO.getTravelReqID());
        transferDetails.setArrivalCountry(transferDetailsDTO.getArrivalCountry());
        transferDetails.setArrivalDate(TimeAndDateParser.parseDate(transferDetailsDTO.getArrivalDate()));
        transferDetails.setArrivalTime(TimeAndDateParser.parseTime(transferDetailsDTO.getArrivalTime()));
        transferDetails.setArriveTo(transferDetailsDTO.getArriveTo());
        transferDetails.setDepartFrom(transferDetailsDTO.getDepartFrom());
        transferDetails.setDepartingCountry(transferDetailsDTO.getDepartingCountry());
        transferDetails.setDepartureDate(TimeAndDateParser.parseDate(transferDetailsDTO.getDepartureDate()));
        transferDetails.setDepartureTime(TimeAndDateParser.parseTime(transferDetailsDTO.getDepartureTime()));
        transferDetails.setEmployee(employeeMapper.employeeDTOtoEmployee(transferDetailsDTO.getEmployee()));
        transferDetails.setPreviousId(transferDetailsDTO.getPreviousId());
        transferDetails.setUpdateDate(TimeAndDateParser.parseDate(transferDetailsDTO.getUpdateDate()));
        transferDetails.setUpdateTime(TimeAndDateParser.parseTime(transferDetailsDTO.getUpdateTime()));
        transferDetails.setFlightId(transferDetailsDTO.getFlightId());
        if(transferDetailsDTO.getReservationDate() == null){
            transferDetails.setReservationDate(null);
        }else{
            transferDetails.setReservationDate(TimeAndDateParser.parseDate(transferDetailsDTO.getReservationDate()));
        }
        if(transferDetailsDTO.getReservationTime() == null){
            transferDetails.setReservationTime(null);
        }else{
            transferDetails.setReservationTime(TimeAndDateParser.parseTime(transferDetailsDTO.getReservationTime()));
        }
        if(transferDetailsDTO.getTransferDate() == null){
            transferDetails.setTransferDate(null);
        }else{
            transferDetails.setTransferDate(TimeAndDateParser.parseDate(transferDetailsDTO.getTransferDate()));
        }
        if(transferDetailsDTO.getTransferTime() == null){
            transferDetails.setTransferTime(null);
        }else{
            transferDetails.setTransferTime(TimeAndDateParser.parseTime(transferDetailsDTO.getTransferTime()));
        }
        if(transferDetailsDTO.getTransferCity() == null){
            transferDetails.setTransferCity(null);
        }else{
            transferDetails.setTransferCity(transferDetailsDTO.getTransferCity());
        }
        if(transferDetailsDTO.getTransferAddress() == null){

            transferDetails.setTransferAddress(null);
        }else{
            transferDetails.setTransferAddress(transferDetailsDTO.getTransferAddress());
        }



        return transferDetails;
    }
}
