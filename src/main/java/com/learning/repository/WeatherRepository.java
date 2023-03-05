package com.learning.repository;

import com.learning.entity.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherInfo, Long> {

    WeatherInfo findByPincode(Integer pincode);
}
