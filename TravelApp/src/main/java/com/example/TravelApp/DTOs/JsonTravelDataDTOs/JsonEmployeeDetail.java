package com.example.TravelApp.DTOs.JsonTravelDataDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonEmployeeDetail {
    public long id;
    public String email_Id;
    public int employeeID;
    public String employee_Firstname;
    public String designation_Name;
    public String employee_Lastname;
}
