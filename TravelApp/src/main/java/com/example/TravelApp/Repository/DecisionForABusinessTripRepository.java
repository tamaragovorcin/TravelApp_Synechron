package com.example.TravelApp.Repository;

import com.example.TravelApp.Model.DecisionForABusinessTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DecisionForABusinessTripRepository extends JpaRepository<DecisionForABusinessTrip, Long> {

    @Query(value = "SELECT a FROM DecisionForABusinessTrip a WHERE a.travelReqID = ?1")
    DecisionForABusinessTrip findByTravelReqID(String travelReqID);
}
