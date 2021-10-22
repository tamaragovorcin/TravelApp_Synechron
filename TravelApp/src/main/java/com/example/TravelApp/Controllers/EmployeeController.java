package com.example.TravelApp.Controllers;

import com.example.TravelApp.DTOs.EmployeeDTO;
import com.example.TravelApp.Service.Interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping( value = "/api/employee" , produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping(value = "/all")
    public ResponseEntity<Set<EmployeeDTO>> findAll(){
        Set<EmployeeDTO> employeeDTOS= employeeService.findAll();
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/create")
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1 = employeeService.save(employeeDTO);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<EmployeeDTO> update(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1 =  employeeService.update(employeeDTO);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.OK);
    }



}