package com.example.TravelApp.Unit;

import com.example.TravelApp.Controllers.EmployeeController;
import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.Mapper.EmployeeMapper;
import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Service.Implementations.EmployeeServiceImpl;
import com.example.TravelApp.Unit.Constants.EmployeeConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.TravelApp.Unit.Constants.AccommodationConstants.ID;
import static com.example.TravelApp.Unit.Constants.EmployeeConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private EmployeeController employeeControllerMock;

    @Mock
    private EmployeeMapper employeeMapperMock;


    @Test
    public void testShouldFindAllEmployees()  {

        List<Employee> employees = new ArrayList<>(getEmployeeSet());
        Set<EmployeeDTO> employeeDTOS = employeeMapperMock.getEmployeeDTOS(employees);
        when(employeeServiceMock.findAll()).thenReturn(employeeDTOS);

        ResponseEntity<Set<EmployeeDTO>> result =  employeeControllerMock.findAll();

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(),employeeDTOS);

    }

    @Test
    public void testShouldCreateEmployee() {

        List<Employee> employeeList = new ArrayList<>(getEmployeeSet());
        Set<EmployeeDTO> employeeDTOSet = employeeMapperMock.getEmployeeDTOS(employeeList);


        EmployeeDTO employeeDTO = employeeMapperMock.employeeToDTO(EMPLOYEE);
        when(employeeServiceMock.findAll()).thenReturn(employeeDTOSet);
        when(employeeServiceMock.save(employeeDTO)).thenReturn(employeeDTO);

        int dbSizeBeforeCreate = employeeServiceMock.findAll().size();
        ResponseEntity<EmployeeDTO> result = employeeControllerMock.create(employeeDTO);

        employeeList.add(employeeMapperMock.employeeDTOtoEmployee(result.getBody()));
        employeeDTOSet.add(result.getBody());
        when(employeeServiceMock.findAll()).thenReturn(employeeDTOSet);

        assertEquals(employeeServiceMock.findAll().size(),dbSizeBeforeCreate + 1);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        verify(employeeServiceMock, times(2)).findAll();
        verify(employeeServiceMock, times(1)).save(employeeDTO);

    }


    @Test
    @Transactional
    @Rollback(true)
    public void testShouldUpdateEmployee() {

        // 1. Definisanje ponašanja
        when(employeeServiceMock.findByEmployeeId(EMPLOYEE_ID)).thenReturn(new Employee(ID, EMPLOYEE_ID,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, EmployeeConstants.TRAVEL_DETAILS, EmployeeConstants.ACCOMMODATION_DETAILS,TRANSFER_DETAILS,POSITION));

        // 2. Akcija
        Employee employeeForUpdate = employeeServiceMock.findByEmployeeId(EMPLOYEE_ID);
        employeeForUpdate.setFirstName(NEW_FIRST_NAME);
        employeeForUpdate.setLastName(NEW_LAST_NAME);

        EmployeeDTO employeeDTOUpdate = new EmployeeDTO();
        employeeDTOUpdate.setId(employeeForUpdate.getId());
        employeeDTOUpdate.setEmployeeId(employeeForUpdate.getEmployeeId());
        employeeDTOUpdate.setAccommodationDetails(employeeForUpdate.getAccommodationDetails());
        employeeDTOUpdate.setFirstName(employeeForUpdate.getFirstName());
        employeeDTOUpdate.setLastName(employeeForUpdate.getLastName());
        employeeDTOUpdate.setEmail(employeeForUpdate.getEmail());
        employeeDTOUpdate.setDesignationName(employeeForUpdate.getDesignationName());
        employeeDTOUpdate.setTravelDetails(employeeForUpdate.getTravelDetails());

        when(employeeServiceMock.update(employeeDTOUpdate)).thenReturn(employeeDTOUpdate);
       EmployeeDTO employeeDTO = employeeServiceMock.update(employeeDTOUpdate);

        // 3. Verifikacija
        assertThat(employeeDTO).isNotNull();
        employeeForUpdate = employeeServiceMock.findByEmployeeId(EMPLOYEE_ID);
        assertThat(employeeForUpdate.getFirstName()).isEqualTo(NEW_FIRST_NAME);
        assertThat(employeeForUpdate.getLastName()).isEqualTo(NEW_LAST_NAME);
        verify(employeeServiceMock, times(2)).findByEmployeeId(EMPLOYEE_ID);
        verify(employeeServiceMock, times(1)).update(employeeDTO);
        verifyNoMoreInteractions(employeeServiceMock);
    }


    @Test(expected = NullPointerException.class)
    public void testShouldNotUpdateEmployee() {
        // 1. Definisanje ponašanja

        when(employeeServiceMock.findByEmployeeId(EMPLOYEE_ID)).thenReturn(new Employee(ID, EMPLOYEE_ID,EMAIL, FIRST_NAME, LAST_NAME, DESIGNATION_NAME, EmployeeConstants.TRAVEL_DETAILS, EmployeeConstants.ACCOMMODATION_DETAILS,TRANSFER_DETAILS,POSITION));

        // 2. Akcija
        Employee employeeForUpdate = employeeServiceMock.findByEmployeeId(EMPLOYEE_ID2);
        employeeForUpdate.setFirstName(NEW_FIRST_NAME);
        employeeForUpdate.setLastName(NEW_LAST_NAME);

        EmployeeDTO employeeDTOUpdate = new EmployeeDTO();
        employeeDTOUpdate.setId(employeeForUpdate.getId());
        employeeDTOUpdate.setEmployeeId(employeeForUpdate.getEmployeeId());
        employeeDTOUpdate.setAccommodationDetails(employeeForUpdate.getAccommodationDetails());
        employeeDTOUpdate.setFirstName(employeeForUpdate.getFirstName());
        employeeDTOUpdate.setLastName(employeeForUpdate.getLastName());
        employeeDTOUpdate.setEmail(employeeForUpdate.getEmail());
        employeeDTOUpdate.setDesignationName(employeeForUpdate.getDesignationName());
        employeeDTOUpdate.setTravelDetails(employeeForUpdate.getTravelDetails());

        when(employeeServiceMock.update(employeeDTOUpdate)).thenReturn(employeeDTOUpdate);
        EmployeeDTO employeeDTO = employeeServiceMock.update(employeeDTOUpdate);

        // 3. Verifikacija
        assertThat(employeeDTO).isNotNull();
        employeeForUpdate = employeeServiceMock.findByEmployeeId(EMPLOYEE_ID);
        assertThat(employeeForUpdate.getFirstName()).isEqualTo(NEW_FIRST_NAME);
        assertThat(employeeForUpdate.getLastName()).isEqualTo(NEW_LAST_NAME);
        verify(employeeServiceMock, times(2)).findByEmployeeId(EMPLOYEE_ID);
        verify(employeeServiceMock, times(1)).update(employeeDTO);
        verifyNoMoreInteractions(employeeServiceMock);
    }
}
