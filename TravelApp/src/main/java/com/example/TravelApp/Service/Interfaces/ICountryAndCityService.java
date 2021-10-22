package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.Model.CountryAndCity;

public interface ICountryAndCityService {
    CountryAndCity findByCountryAndCity(String country, String city);
}
