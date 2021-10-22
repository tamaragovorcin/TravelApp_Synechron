package com.example.TravelApp.Repository;

import com.example.TravelApp.Model.AccommodationDetails;
import com.example.TravelApp.Model.TravelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AccomodationDetailsRepository extends JpaRepository<AccommodationDetails, Long> {
    @Query(value = "SELECT t FROM AccommodationDetails t WHERE t.accommodationRequestNo = ?1")
    Set<AccommodationDetails> findByAccommodationRequestNo(String id);
}
