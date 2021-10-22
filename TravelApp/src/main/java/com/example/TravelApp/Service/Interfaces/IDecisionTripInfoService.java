package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Model.DecisionTripInfo;
import java.util.List;
import java.util.Set;

public interface IDecisionTripInfoService {
    List<DecisionTripInfo> saveAll(Set<DecisionTripInfo> decisionTripInfoList, DecisionForABusinessTrip savedDecision);
    DecisionTripInfo save(DecisionTripInfo decisionTripInfo);
}
