package com.example.TravelApp.Repository;

import com.example.TravelApp.Model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(value = "SELECT a FROM Position a WHERE a.name = :positionName")
    Position findByName(String positionName);
}
