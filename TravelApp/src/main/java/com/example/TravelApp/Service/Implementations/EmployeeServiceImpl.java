package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.DTOs.JsonTravelDataDTOs.JsonEmployeeDetail;
import com.example.TravelApp.Mapper.EmployeeMapper;
import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Model.Position;
import com.example.TravelApp.Repository.EmployeeRepository;
import com.example.TravelApp.Service.Interfaces.IEmployeeService;
import com.example.TravelApp.Service.Interfaces.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    IPositionService positionService;

    @Override
    public Set<EmployeeDTO> findAll() {
        List<Employee> employees =  employeeRepository.findAll();
        return employeeMapper.getEmployeeDTOS(employees);
    }


    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(employeeMapper.employeeDTOtoEmployee(employeeDTO));
        return employeeMapper.employeeToDTO(employee);
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(employeeMapper.employeeDTOtoEmployee(employeeDTO));
        return employeeMapper.employeeToDTO(employee);
    }


    @Override
    public Employee findByEmployeeId(String id) {
        return this.employeeRepository.findByEmployeeId(id);
    }

    @Override
    public void parseEmployeeFromJson(List<JsonEmployeeDetail> employee_details) {
        for (JsonEmployeeDetail jsonEmployeeDetail: employee_details) {
            if(findByEmployeeId(String.valueOf(jsonEmployeeDetail.getEmployeeID())) == null) {
                Employee employee= employeeMapper.jsonEmployeeToEmployee(jsonEmployeeDetail);
                Position position = positionService.getTranslatedPositionForEmployee(employee);
                if(position!=null) {
                    employee.setPosition(position);
                }
                employeeRepository.save(employee);
            }
        }
    }

    @Override
    public String findPositionName(String employeeId) {
        String position = employeeRepository.findPositionName(employeeId);
        return (position==null) ? "" : position;
    }

}
