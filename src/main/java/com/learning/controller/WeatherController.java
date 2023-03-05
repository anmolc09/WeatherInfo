package com.learning.controller;

import com.learning.entity.WeatherInfo;
import com.learning.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherInfo> getWeather(@RequestParam Integer pincode) {
        WeatherInfo weather = weatherService.getWeather(pincode);
        return ResponseEntity.ok(weather);
        //return new ResponseEntity<>(weather, HttpStatus.ACCEPTED);
    }

}
