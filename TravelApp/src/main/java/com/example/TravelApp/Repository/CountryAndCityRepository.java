package com.example.TravelApp.Repository;

import com.example.TravelApp.Model.CountryAndCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryAndCityRepository extends JpaRepository<CountryAndCity, Long> {

    @Query(value = "SELECT a FROM CountryAndCity a WHERE a.country = :country AND a.city=:city")
    CountryAndCity findByCountryAndCity(String country, String city);
}
