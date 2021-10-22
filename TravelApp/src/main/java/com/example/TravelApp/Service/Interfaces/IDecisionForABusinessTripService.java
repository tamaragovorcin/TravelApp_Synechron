package com.example.TravelApp.Service.Interfaces;
import com.example.TravelApp.DTOs.TravelDetailsDTO;
import com.example.TravelApp.Model.DecisionForABusinessTrip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IDecisionForABusinessTripService {

    DecisionForABusinessTrip save(List<TravelDetailsDTO> travelDetailsDTOList);
    List<DecisionForABusinessTrip> findAll();

    DecisionForABusinessTrip findByTravelReqID(String decisionNumber);
    List<DecisionForABusinessTrip> saveAll(Set<TravelDetailsDTO> travelDetailsDTOs);
    String findNextDecisionNumber();

    void scheduleDecisionSaving(Set<TravelDetailsDTO> travelDetailsDTOs);

    byte[] exportFileByDecisionId(String decisionId);

    byte[] exportFileWithCalculationByTravelReqId(String travelReqId);
}
