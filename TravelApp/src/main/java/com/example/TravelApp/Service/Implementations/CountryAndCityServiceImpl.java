package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.Model.CountryAndCity;
import com.example.TravelApp.Repository.CountryAndCityRepository;
import com.example.TravelApp.Service.Interfaces.ICountryAndCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryAndCityServiceImpl implements ICountryAndCityService {

    @Autowired
    CountryAndCityRepository countryAndCityRepository;

    @Override
    public CountryAndCity findByCountryAndCity(String country, String city) {
        return countryAndCityRepository.findByCountryAndCity(country,city);
    }
}
