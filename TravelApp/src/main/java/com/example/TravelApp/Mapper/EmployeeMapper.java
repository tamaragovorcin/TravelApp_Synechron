package com.example.TravelApp.Mapper;

import com.example.TravelApp.DTOs.AccommodationDetailsDTO;
import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeDetail;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeMapper {

    public  EmployeeDTO employeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDesignationName(employee.getDesignationName());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());

        return employeeDTO;
    }

    public Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO) {
        Employee employee= new Employee();
        employee.setId(employeeDTO.getId());
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDesignationName(employeeDTO.getDesignationName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());

        return employee;
    }


    public Employee jsonEmployeeToEmployee(JsonEmployeeDetail jsonEmployeeDetail) {

        Employee employee= new Employee();

        employee.setEmployeeId(String.valueOf(jsonEmployeeDetail.getEmployeeID()));
        employee.setEmail(jsonEmployeeDetail.getEmail_Id());
        employee.setDesignationName(jsonEmployeeDetail.getDesignation_Name());
        employee.setFirstName(jsonEmployeeDetail.getEmployee_Firstname());
        employee.setLastName(jsonEmployeeDetail.getEmployee_Lastname());

        return employee;
    }

    public Set<EmployeeDTO> getEmployeeDTOS(List<Employee> employees) {

        Set<EmployeeDTO> employeeDTOS = new HashSet<>();
        for (Employee employee : employees) {
            employeeDTOS.add(employeeToDTO(employee));
        }
        return employeeDTOS;
    }
}
