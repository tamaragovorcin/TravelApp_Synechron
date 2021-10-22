package com.example.TravelApp.Unit.Constants;

import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Model.DecisionTripInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static com.example.TravelApp.Unit.Constants.DecisionForABusinessTripConstants.*;

public class DecisionTripInfoConstants {
    public static final long ID = 1;
    public static final String ARRIVAL_COUNTRY = "India";
    public static final String ARRIVAL_CITY = "Mumbai";
    public static final String DEPARTURE_DATE = "02-02-2021";


    public static DecisionTripInfo getDecisionTripInfo() {
        DecisionForABusinessTrip decision = getDecisionForBusinessTrip();
        return new DecisionTripInfo(ID,ARRIVAL_COUNTRY,ARRIVAL_CITY,DEPARTURE_DATE,decision);
    }
    public static List<DecisionTripInfo> getDecisionTripInfoList() {
        DecisionForABusinessTrip decision = getDecisionForBusinessTrip();
        List<DecisionTripInfo> decisionTripInfoList = new ArrayList<>();
        decisionTripInfoList.add(getDecisionTripInfo());
        return decisionTripInfoList;
    }
    public static DecisionTripInfo getDecisionTripInfoForCreate() {
        DecisionForABusinessTrip decision = getDecisionForBusinessTrip();
        DecisionTripInfo decisionTripInfo = new DecisionTripInfo();
        decisionTripInfo.setArrivalCountry(ARRIVAL_COUNTRY);
        decisionTripInfo.setArrivalCity(ARRIVAL_CITY);
        decisionTripInfo.setDepartureDate(DEPARTURE_DATE);
        decisionTripInfo.setDecisionForABusinessTrip(decision);
        return decisionTripInfo;
    }
    public static Set<DecisionTripInfo> getDecisionTripInfoListForCreate() {
        Set<DecisionTripInfo> decisionTripInfoList = new HashSet<>();
        decisionTripInfoList.add(getDecisionTripInfoForCreate());
        decisionTripInfoList.add(getDecisionTripInfoForCreate());
        return decisionTripInfoList;
    }


}
