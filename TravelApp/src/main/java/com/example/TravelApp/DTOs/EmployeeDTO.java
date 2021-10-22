package com.example.TravelApp.DTOs;

import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.TravelDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

    private long id;
    private String employeeId;
    private String email;
    private String firstName;
    private String lastName;
    private String designationName;
    private Set<TravelDetails> travelDetails = new HashSet<TravelDetails>();
    private Set<AccommodationDetails> accommodationDetails = new HashSet<AccommodationDetails>();
}