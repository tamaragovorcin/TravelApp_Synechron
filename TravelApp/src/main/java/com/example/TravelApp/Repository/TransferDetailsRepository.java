package com.example.TravelApp.Repository;

import com.example.TravelApp.Model.TransferDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TransferDetailsRepository extends JpaRepository<TransferDetails, Long> {

    @Query(value = "SELECT t FROM TransferDetails t WHERE t.travelReqID = ?1")
    Set<TransferDetails> findByTransferReqID(String id);

    @Query(value = "SELECT t FROM TransferDetails t WHERE t.departureDate >= :departureDate and t.arrivalDate < :arrivalDate")
    List<TransferDetails> findAllTransferDetailsBetweenDates(LocalDate departureDate, LocalDate arrivalDate);
}
