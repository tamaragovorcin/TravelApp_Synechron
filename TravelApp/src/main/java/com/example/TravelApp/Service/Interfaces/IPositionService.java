package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Model.Position;
import org.springframework.stereotype.Service;

@Service
public interface IPositionService {
    Position findByName(String positionName);

    Position getTranslatedPositionForEmployee(Employee employee);
}
