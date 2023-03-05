package com.learning.controller;

import com.learning.entity.WeatherDetails;
import com.learning.entity.WeatherInfo;
import com.learning.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class WeatherInfoControllerTest {

    @Mock
    private WeatherService weatherService;
    @InjectMocks
    private WeatherController weatherController;
    private WeatherInfo expectedWeatherInfo;

    @BeforeEach
    public void setUp() {
        this.expectedWeatherInfo = new WeatherInfo();
        expectedWeatherInfo.setPincode(121002);
        expectedWeatherInfo.setName("Faridabad");
        expectedWeatherInfo.setCreatedAt(LocalDate.now());
        WeatherDetails weatherDetails = new WeatherDetails();
        weatherDetails.setTemp_max(30.0);
        weatherDetails.setPressure(1000d);
        weatherDetails.setDescription("clear sky");
        weatherDetails.setTemp_min(20.0);
        weatherDetails.setTemp(25.0);
        weatherDetails.setHumidity(40.0);
        expectedWeatherInfo.setWeatherDetails(weatherDetails);
    }

    @Test
    void testWeatherInfogetWeatherInfo() throws Exception {
        Mockito.when(weatherService.getWeather(121002)).thenReturn(expectedWeatherInfo);
        ResponseEntity<WeatherInfo> actualWeather = weatherController.getWeather(121002);
        if (actualWeather.getStatusCode().is2xxSuccessful()) {
            Assertions.assertEquals(expectedWeatherInfo.getWeatherDetails().getTemp(), actualWeather.getBody().getWeatherDetails().getTemp());
            Assertions.assertEquals(expectedWeatherInfo.getWeatherDetails().getHumidity(), actualWeather.getBody().getWeatherDetails().getHumidity());
            verify(weatherService, Mockito.times(1)).getWeather(121002);
        }
    }
}
