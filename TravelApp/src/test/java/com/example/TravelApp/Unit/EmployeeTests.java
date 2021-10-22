package com.example.TravelApp.Unit;

import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeDetail;
import com.example.TravelApp.Mapper.EmployeeMapper;
import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Repository.EmployeeRepository;
import com.example.TravelApp.Service.Implementations.EmployeeServiceImpl;
import com.example.TravelApp.Service.Interfaces.IPositionService;
import com.example.TravelApp.Unit.Constants.EmployeeConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

import static com.example.TravelApp.Unit.Constants.AccommodationConstants.ID;
import static com.example.TravelApp.Unit.Constants.EmployeeConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTests {

    @Mock
    private EmployeeRepository employeeRepositoryMock;
    @InjectMocks
    private Employee employeeMock;
    @Mock
    private EmployeeDTO employeeDTOMock;
    @Mock
    private EmployeeMapper employeeMapperMock;
    @Mock
    private EmployeeServiceImpl employeeServiceMock;
    @Mock
    private IPositionService positionService;

    @Test
    public void testShouldFindAllEmployee() {
        // 1. Definisanje ponašanja
        List<Employee> employeeList = new ArrayList<>(getEmployeeSet());
        when(employeeRepositoryMock.findAll()).thenReturn(employeeList);

        // 2. Akcija
        List<Employee> dbEmployees = employeeRepositoryMock.findAll();


        // 3. Verifikacija
        assertEquals(dbEmployees.size(),1);
        assertEquals(employeeList, dbEmployees);
        verify(employeeRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepositoryMock);
    }



    @Test
    @Transactional
    @Rollback(true)
    public void testShouldSaveEmployee() {
        // 1.Ponasanje
        List<Employee> employeeList = new ArrayList<>(getEmployeeSet());
        when(employeeRepositoryMock.findAll()).thenReturn(employeeList);
        when(employeeRepositoryMock.save(EmployeeConstants.EMPLOYEE)).thenReturn(EmployeeConstants.EMPLOYEE);


        // 2. Akcija
        int dbSizeBeforeAdd = employeeRepositoryMock.findAll().size();
        Employee dbEmployee = employeeRepositoryMock.save(EmployeeConstants.EMPLOYEE);
        when(employeeServiceMock.findAll()).thenReturn(getEmployeeSetDTO());

        //3.Verifikacija
        assertThat(dbEmployee).isNotNull();
        Set<EmployeeDTO> employeeDTOS = employeeServiceMock.findAll();
        assertThat(employeeDTOS).hasSize(dbSizeBeforeAdd + 1);
        verify(employeeRepositoryMock, times(1)).findAll();
        verify(employeeRepositoryMock, times(1)).save(EmployeeConstants.EMPLOYEE);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testShouldUpdateEmployee() {
        // 1. Definisanje ponašanja

        when(employeeRepositoryMock.findById(ID)).thenReturn(Optional.of(new Employee(ID, EMPLOYEE_ID,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, EmployeeConstants.TRAVEL_DETAILS, EmployeeConstants.ACCOMMODATION_DETAILS,TRANSFER_DETAILS,POSITION)));

        // 2. Akcija
        Employee employeeForUpdate = employeeRepositoryMock.findById(ID).get();
        employeeForUpdate.setFirstName(NEW_FIRST_NAME);
        employeeForUpdate.setLastName(NEW_LAST_NAME);

        when(employeeRepositoryMock.save(employeeForUpdate)).thenReturn(employeeForUpdate);
        employeeForUpdate = employeeRepositoryMock.save(employeeForUpdate);

        // 3. Verifikacija
        assertThat(employeeForUpdate).isNotNull();
        employeeForUpdate = employeeRepositoryMock.findById(ID).get();
        assertThat(employeeForUpdate.getFirstName()).isEqualTo(NEW_FIRST_NAME);
        assertThat(employeeForUpdate.getLastName()).isEqualTo(NEW_LAST_NAME);
        verify(employeeRepositoryMock, times(2)).findById(ID);
        verify(employeeRepositoryMock, times(1)).save(employeeForUpdate);
        verifyNoMoreInteractions(employeeRepositoryMock);
    }


    @Test(expected = NoSuchElementException.class)
    public void testShouldNotUpdateEmployee() {
        // 1. Definisanje ponašanja

        when(employeeRepositoryMock.findById(ID)).thenReturn(Optional.of(new Employee(ID, EMPLOYEE_ID,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, EmployeeConstants.TRAVEL_DETAILS, EmployeeConstants.ACCOMMODATION_DETAILS,TRANSFER_DETAILS,POSITION)));

        // 2. Akcija
        Employee employeeForUpdate = employeeRepositoryMock.findById(ID2).get();
        employeeForUpdate.setFirstName(NEW_FIRST_NAME);
        employeeForUpdate.setLastName(NEW_LAST_NAME);

        when(employeeRepositoryMock.save(employeeForUpdate)).thenReturn(employeeForUpdate);
        employeeForUpdate = employeeRepositoryMock.save(employeeForUpdate);

        // 3. Verifikacija
        assertThat(employeeForUpdate).isNotNull();
        employeeForUpdate = employeeRepositoryMock.findById(ID).get();
        assertThat(employeeForUpdate.getFirstName()).isEqualTo(NEW_FIRST_NAME);
        assertThat(employeeForUpdate.getLastName()).isEqualTo(NEW_LAST_NAME);
        verify(employeeRepositoryMock, times(2)).findById(ID);
        verify(employeeRepositoryMock, times(1)).save(employeeForUpdate);
        verifyNoMoreInteractions(employeeRepositoryMock);
    }

    @Test
    public void testShouldFindByEmployeeId() {
        // 1. Definisanje ponašanja
        Employee employee = EmployeeConstants.EMPLOYEE;
        when(employeeRepositoryMock.findByEmployeeId(EMPLOYEE_ID)).thenReturn(employee);

        // 2. Akcija
        Employee employeeDb = employeeRepositoryMock.findByEmployeeId(EMPLOYEE_ID);

        // 3. Verifikacija
        assertEquals(employee, employeeDb);
        verify(employeeRepositoryMock, times(1)).findByEmployeeId(EMPLOYEE_ID);
        verifyNoMoreInteractions(employeeRepositoryMock);
    }

    @Test
    public void testShouldNotFindByEmployeeId() {
        // 1. Definisanje ponašanja
        Employee employee = EmployeeConstants.EMPLOYEE;
        when(employeeRepositoryMock.findByEmployeeId(EMPLOYEE_ID)).thenReturn(employee);

        // 2. Akcija
        Employee employeeDb = employeeRepositoryMock.findByEmployeeId(EMPLOYEE_ID2);

        // 3. Verifikacija
        assertNotEquals(employee, employeeDb);
        verify(employeeRepositoryMock, times(1)).findByEmployeeId(EMPLOYEE_ID2);
        verifyNoMoreInteractions(employeeRepositoryMock);
    }


    @Test
    public void testShouldFindByPositionName() {
        // 1. Definisanje ponašanja
        String position = POSITION.getName();
        when(employeeRepositoryMock.findPositionName(EMPLOYEE_ID)).thenReturn(position);

        // 2. Akcija
        String positionReturn = employeeRepositoryMock.findPositionName(EMPLOYEE_ID);

        // 3. Verifikacija
        assertEquals(positionReturn, position);
        verify(employeeRepositoryMock, times(1)).findPositionName(EMPLOYEE_ID);
        verifyNoMoreInteractions(employeeRepositoryMock);
    }

    @Test
    public void testShouldNotFindByPositionName() {
        // 1. Definisanje ponašanja
        String position = POSITION.getName();
        when(employeeRepositoryMock.findPositionName(EMPLOYEE_ID)).thenReturn(position);

        // 2. Akcija
        String positionReturn = employeeRepositoryMock.findPositionName(EMPLOYEE_ID2);

        // 3. Verifikacija
        assertNotEquals(positionReturn, position);
        verify(employeeRepositoryMock, times(1)).findPositionName(EMPLOYEE_ID2);
        verifyNoMoreInteractions(employeeRepositoryMock);
    }

    @Test
    public void testShouldParseEmployeeFromJson() {
        // 1. Definisanje ponašanja
        List<JsonEmployeeDetail> jsonEmployeeDetails = new ArrayList<>();
        jsonEmployeeDetails.add(JSON_EMPLOYEE_DETAIL);

        // 2. Akcija
        employeeServiceMock.parseEmployeeFromJson(jsonEmployeeDetails);

        // 3. Verifikacija
        verify(employeeServiceMock, times(1)).parseEmployeeFromJson(jsonEmployeeDetails);
        verifyNoMoreInteractions(employeeServiceMock);
    }

    // da li treba ako je input null da se bacaju neke greske?
    @Test
    public void testShouldNotParseEmployeeFromJson() {
        // 1. Definisanje ponašanja
        List<JsonEmployeeDetail> jsonEmployeeDetails = new ArrayList<>();
        jsonEmployeeDetails.add(JSON_EMPLOYEE_DETAIL);

        // 2. Akcija
        employeeServiceMock.parseEmployeeFromJson(jsonEmployeeDetails);

        // 3. Verifikacija
        verify(employeeServiceMock, times(1)).parseEmployeeFromJson(jsonEmployeeDetails);
        verifyNoMoreInteractions(employeeServiceMock);
    }
}
