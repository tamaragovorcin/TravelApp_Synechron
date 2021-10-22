package com.example.TravelApp.Unit.Constants;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeDetail;
import com.example.TravelApp.Model.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class EmployeeConstants {

    public static final long ID = 1;
    public static final long ID2 = 2;
    public static final long ID3 = 3;
    public static final  String EMPLOYEE_ID = "12345";
    public static final  String EMPLOYEE_ID2 = "23456";
    public static final  String EMPLOYEE_ID3 = "1";
    public static final  String EMAIL = "email";
    public static final  String FIRST_NAME = "First name";
    public static final  String NEW_FIRST_NAME = "New first name";
    public static final  String NEW_LAST_NAME = "New last name";
    public static final  String LAST_NAME = "Last name";
    public static final  String DESIGNATION_NAME = "Designation name";
    public static final  Set<TravelDetails> TRAVEL_DETAILS = new HashSet<TravelDetails>();
    public static final  Set<AccommodationDetails> ACCOMMODATION_DETAILS = new HashSet<AccommodationDetails>();
    public static final  Set<TransferDetails> TRANSFER_DETAILS = new HashSet<TransferDetails>();
    public static final  Position POSITION = new Position(ID, "Position", new HashSet<>());

    public static final Employee EMPLOYEE = new Employee(ID, EMPLOYEE_ID,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, TRAVEL_DETAILS, ACCOMMODATION_DETAILS,TRANSFER_DETAILS,POSITION);
    public static final EmployeeDTO EMPLOYEE_DTO = new EmployeeDTO(ID, EMPLOYEE_ID,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, TRAVEL_DETAILS, ACCOMMODATION_DETAILS);
    public static final EmployeeDTO EMPLOYEE_DTO2 = new EmployeeDTO(ID2, EMPLOYEE_ID2,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, TRAVEL_DETAILS, ACCOMMODATION_DETAILS);
    public static final EmployeeDTO EMPLOYEE_DTO3 = new EmployeeDTO(ID3, EMPLOYEE_ID3,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, TRAVEL_DETAILS, ACCOMMODATION_DETAILS);
    public static final Employee EMPLOYEE_UPDATE = new Employee(ID, EMPLOYEE_ID,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, null, null,TRANSFER_DETAILS,POSITION);

    public  static final JsonEmployeeDetail JSON_EMPLOYEE_DETAIL = new JsonEmployeeDetail(ID, EMAIL, Integer.parseInt(EMPLOYEE_ID), FIRST_NAME, DESIGNATION_NAME, LAST_NAME);

    public static final Set<Employee> getEmployeeSet() {

        Set<Employee> employeeSet = new HashSet<>();

        employeeSet.add(EMPLOYEE);

        return employeeSet;
    }

    public static final Set<EmployeeDTO> getEmployeeSetDTO() {

        Set<EmployeeDTO> employeeDTOS = new HashSet<>();


        employeeDTOS.add(EMPLOYEE_DTO);


        employeeDTOS.add(EMPLOYEE_DTO2);

        return employeeDTOS;
    }


}
