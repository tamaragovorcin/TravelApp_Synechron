package com.example.TravelApp.Unit;

import com.example.TravelApp.Model.CountryAndCity;
import com.example.TravelApp.Repository.CountryAndCityRepository;
import com.example.TravelApp.Service.Implementations.CountryAndCityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static com.example.TravelApp.Unit.Constants.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryAndCityTests {
    @Mock
    private CountryAndCityRepository countryAndCityRepositoryMock;

    @Mock
    private CountryAndCity countryAndCityMock;

    @InjectMocks
    private CountryAndCityServiceImpl countryAndCityServiceMock;

    @Test
    public void testShouldFindCountryAndCity() {
        when(countryAndCityRepositoryMock.findByCountryAndCity(COUNTRY,CITY)).thenReturn(countryAndCityMock);

        CountryAndCity countryAndCityDb = countryAndCityServiceMock.findByCountryAndCity(COUNTRY,CITY);

        assertEquals(countryAndCityMock, countryAndCityDb);
        verify(countryAndCityRepositoryMock, times(1)).findByCountryAndCity(COUNTRY,CITY);
        verifyNoMoreInteractions(countryAndCityRepositoryMock);
    }
}
