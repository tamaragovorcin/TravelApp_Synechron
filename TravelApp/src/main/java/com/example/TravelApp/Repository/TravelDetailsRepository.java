package com.example.TravelApp.Repository;

import com.example.TravelApp.Model.TravelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TravelDetailsRepository extends JpaRepository<TravelDetails, Long> {

    @Query(value = "SELECT t FROM TravelDetails t WHERE t.travelReqID = ?1")
    Set<TravelDetails> findByTravelReqID(String id);

    @Query(value = "SELECT t FROM TravelDetails t WHERE t.departureDate >= :departureDate and t.arrivalDate <= :arrivalDate")
    List<TravelDetails> findAllTravelDetailsBetweenDates(LocalDate departureDate, LocalDate arrivalDate);
}
