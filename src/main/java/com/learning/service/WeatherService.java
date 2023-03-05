package com.learning.service;

import com.learning.entity.WeatherDetails;
import com.learning.entity.WeatherInfo;
import com.learning.model.WeatherResponse;
import com.learning.model.ZipResponse;
import com.learning.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    @Value("${api.key}")
    private String API_KEY;
    @Value("${api.data.url}")
    private String API_DATA_URL;
    @Value("${api.zip.url}")
    private String API_ZIP_URL;

    private final WeatherRepository weatherRepo;

    public WeatherInfo getWeather(Integer pincode) {
        WeatherInfo response = weatherRepo.findByPincode(pincode);
        if(response != null){
            Long id = response.getId();
            response = getWeatherInfo(pincode);
            response.setId(id);
            weatherRepo.save(response);
            log.info("Getting data from db...");
            return response;
        }

        WeatherInfo weatherInfo = getWeatherInfo(pincode);

        log.info("Saving data in db...");

        weatherRepo.save(weatherInfo);
        return weatherInfo;
    }


    private WeatherInfo getWeatherInfo(Integer pincode) {
        ResponseEntity<ZipResponse> responseEntity = new RestTemplate()
                .getForEntity(API_ZIP_URL + pincode + ",in&appid=" + API_KEY,
                        ZipResponse.class);
        Double lat = responseEntity.getBody().getLat();
        Double lon = responseEntity.getBody().getLon();

        ResponseEntity<WeatherResponse> response = new RestTemplate()
                .getForEntity(API_DATA_URL + lat + "&lon=" + lon + "&appid=" + API_KEY ,
                        WeatherResponse.class);
        WeatherResponse weatherResponse = response.getBody();

        WeatherDetails weatherDetails = new WeatherDetails();
        weatherDetails.setDescription(weatherResponse.getWeather().stream()
                .map(WeatherResponse.Weather::getDescription).collect(Collectors.joining(",")));
        weatherDetails.setTemp(weatherResponse.getMain().getTemp());
        weatherDetails.setHumidity(weatherResponse.getMain().getHumidity());
        weatherDetails.setPressure(weatherResponse.getMain().getPressure());
        weatherDetails.setTemp_max(weatherResponse.getMain().getTemp_max());
        weatherDetails.setTemp_min(weatherResponse.getMain().getTemp_min());

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCountry(weatherResponse.getSys().getCountry());
        weatherInfo.setLat(lat);
        weatherInfo.setLon(lon);
        weatherInfo.setName(weatherResponse.getName());
        weatherInfo.setPincode(pincode);
        weatherInfo.setCreatedAt(LocalDate.now());
        weatherInfo.setWeatherDetails(weatherDetails);
        return weatherInfo;
    }


}

