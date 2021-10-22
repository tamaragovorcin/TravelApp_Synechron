package com.example.TravelApp.Unit.Constants;

import com.example.TravelApp.Model.DecisionForABusinessTrip;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DecisionForABusinessTripConstants {
    public static final long ID = 1;
    public static final String DECISION_NUMBER = "1-2021";
    public static final LocalDate DECISION_DATE =LocalDate.now().plusDays(1);
    public static final String AUTHORIZED_PERSON = "5677842";
    public static final String THE_BASIS_OF_AUTHORIZATION = "";

    public static final String TRAVEL_REQ_ID = "TR020614";
    public static final String EMPLOYEE_FULL_NAME = "First Last";
    public static final String EMPLOYEE_DESIGNATION_NAME = "MANUELNI_TESTER";
    public static final String TRAVEL_PURPOSE = "Events";
    public static final String TRAVEL_DURATION = "5 days";
    public static final String WAYS_OF_TRANSPORT = "all";
    public static final String FREE_ACCOMMODATION_ON_TRIP = "";
    public static final double ADVANCED_PAYMENT_EUR = 50.00;
    public static final double ADVANCED_PAYMENT_RSD = 0.00;

    public static final double DAILY_ALLOWANCE_EUR = 50.00;
    public static final double DAILY_ALLOWANCE_RSD = 0.00;

    public static DecisionForABusinessTrip getDecisionForBusinessTrip() {
        //Set<DecisionTripInfo> decisionTripInfoList = getDecisionTripInfoList();
        return new DecisionForABusinessTrip(ID,DECISION_NUMBER ,DECISION_DATE,AUTHORIZED_PERSON,THE_BASIS_OF_AUTHORIZATION,
                EMPLOYEE_FULL_NAME,EMPLOYEE_DESIGNATION_NAME, TRAVEL_PURPOSE,
                TRAVEL_DURATION,WAYS_OF_TRANSPORT,FREE_ACCOMMODATION_ON_TRIP,ADVANCED_PAYMENT_EUR,ADVANCED_PAYMENT_RSD,
                DAILY_ALLOWANCE_EUR,DAILY_ALLOWANCE_RSD,TRAVEL_REQ_ID,null);
    }


    public static List<DecisionForABusinessTrip> getDecisionsForABusinessTripList() {
        List<DecisionForABusinessTrip> decisionForABusinessTrips = new ArrayList<>();
        decisionForABusinessTrips.add(getDecisionForBusinessTrip());
        return  decisionForABusinessTrips;
    }
}
