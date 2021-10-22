package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.Model.Employee;
import com.example.TravelApp.Model.Position;
import com.example.TravelApp.Repository.PositionRepository;
import com.example.TravelApp.Service.Interfaces.IPositionService;
import org.jopendocument.dom.spreadsheet.Cell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    PositionRepository positionRepository;

    @Override
    public Position findByName(String positionName) {
        return positionRepository.findByName(positionName);
    }

    @Override
    public Position getTranslatedPositionForEmployee(Employee employee) {
        File file = new File("src/main/resources/zaposleni-pozicije.ods");
        return findAppropriatePosition(file, employee);
    }
    public Position findAppropriatePosition(File file, Employee employee) {
        SpreadSheet spreadsheet;
        try {
            spreadsheet = SpreadSheet.createFromFile(file);
            int nRowCount = spreadsheet.getSheet(0).getRowCount();

            for (int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++) {
                Cell cellName = spreadsheet.getSheet(0).getCellAt(0, nRowIndex);
                Cell cellLastName = spreadsheet.getSheet(0).getCellAt(1, nRowIndex);

                if (cellName.getValue().toString().trim().equalsIgnoreCase(employee.getFirstName()) && cellLastName.getValue().toString().trim().equalsIgnoreCase(employee.getLastName())) {
                    Cell positionName = spreadsheet.getSheet(0).getCellAt(2, nRowIndex);

                    String positionNameString = positionName.getValue().toString().trim();

                    try {
                        return findByName(positionNameString);

                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
