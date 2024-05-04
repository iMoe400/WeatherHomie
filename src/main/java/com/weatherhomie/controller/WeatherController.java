package com.weatherhomie.controller;

import com.weatherhomie.weatherModel.ForecastData;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;


@Controller
public class WeatherController {

    public WeatherController() {
    }

    public ForecastData getForecastData() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=51.3197&longitude=7.3392&hourly=temperature_2m&timezone=Europe/Berlin";
        return restTemplate.getForObject(apiUrl, ForecastData.class);
    }
}
