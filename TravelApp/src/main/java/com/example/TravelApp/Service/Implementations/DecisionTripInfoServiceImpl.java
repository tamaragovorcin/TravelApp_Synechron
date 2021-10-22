package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.Model.DecisionForABusinessTrip;
import com.example.TravelApp.Model.DecisionTripInfo;
import com.example.TravelApp.Repository.DecisionTripInfoRepository;
import com.example.TravelApp.Service.Interfaces.IDecisionTripInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DecisionTripInfoServiceImpl implements IDecisionTripInfoService {
    @Autowired
    DecisionTripInfoRepository decisionTripInfoRepository;

    @Override
    public List<DecisionTripInfo> saveAll(Set<DecisionTripInfo> decisionTripInfoList, DecisionForABusinessTrip savedDecision) {
        List<DecisionTripInfo> decisionTripInfos = new ArrayList<>();
        for (DecisionTripInfo decisionTripInfo: decisionTripInfoList) {
            decisionTripInfo.setDecisionForABusinessTrip(savedDecision);
            decisionTripInfos.add(save(decisionTripInfo));
        }
        return decisionTripInfos;
    }

    @Override
    public DecisionTripInfo save(DecisionTripInfo decisionTripInfo) {
        return decisionTripInfoRepository.save(decisionTripInfo);
    }
}
