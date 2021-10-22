package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeDetail;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Model.Employee;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public interface IEmployeeService {
    Set<EmployeeDTO> findAll();
    EmployeeDTO save(EmployeeDTO employeeDTO);
    EmployeeDTO update(EmployeeDTO employeeDTO);
    Employee findByEmployeeId(String id);
    void parseEmployeeFromJson(List<JsonEmployeeDetail> employee_details);
    String findPositionName(String employeeId);
}
