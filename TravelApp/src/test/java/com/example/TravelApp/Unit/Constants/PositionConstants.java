package com.example.TravelApp.Unit.Constants;

import com.example.TravelApp.Model.Position;
import java.util.ArrayList;
import java.util.List;

public class PositionConstants {
    public static final long ID = 1;
    public static final String POSITION_NAME = "MANUELNI_TESTER";

    public static final long ID_2 = 2;
    public static final String POSITION_NAME_2 = "MANUELNI_TESTER_2";

    public static List<Position> getPositionList() {
        List<Position> positionList = new ArrayList<>();
        Position position = new Position(ID_2,POSITION_NAME_2, null);
        positionList.add(position);
        positionList.add(position);

        return positionList;
    }
}
