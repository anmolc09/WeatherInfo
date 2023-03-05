/*
package com.learning.controller;

import com.learning.entity.WeatherInfo;
import com.learning.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

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
        expectedWeatherInfo.setPincode(12345);
        expectedWeatherInfo.setName("XYZ");
        LocalDate for_date = LocalDate.parse("2023-03-02");
        expectedWeatherInfo.setDate(for_date);
        expectedWeatherInfo.setTemperature(20.0);
        expectedWeatherInfo.setHumidity(50);
        expectedWeatherInfo.setPressure(1000);
        expectedWeatherInfo.setWindSpeed(3.46);
        expectedWeatherInfo.setDescription("clear");
    }

    @Test
    void testWeatherInfogetWeatherInfo() throws Exception {
        Mockito.when(weatherService.getWeatherInfo(12345, LocalDate.parse("2023-03-02"))).thenReturn(expectedWeatherInfo);
        ResponseEntity<WeatherInfo> actualWeatherInfo = weatherController.getWeatherInfo(12345, LocalDate.parse("2023-03-02"));
        // verify results
        if (actualWeatherInfo.getStatusCode().is2xxSuccessful()) {
            Assertions.assertEquals(expectedWeatherInfo.getTemperature(), actualWeatherInfo.getBody().getTemperature());
            Assertions.assertEquals(expectedWeatherInfo.getHumidity(), actualWeatherInfo.getBody().getHumidity());
            verify(weatherService, Mockito.times(1)).getWeatherInfo(12345, LocalDate.parse("2023-03-02"));
        }
    }
}
*/
