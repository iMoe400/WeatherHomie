package com.homeboysguckenwetter2.weatherhomie.controller;

import com.homeboysguckenwetter2.weatherhomie.weatherModel.ForecastData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherController {

    public WeatherController() {
    }

    public ForecastData getForecastData() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=51.3197&longitude=7.3392&hourly=temperature_2m&timezone=Europe/Berlin";
        return restTemplate.getForObject(apiUrl, ForecastData.class);
    }
}
