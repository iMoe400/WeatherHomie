package com.weatherhomie.controller;

import com.weatherhomie.weatherModel.TodaysData;
import com.weatherhomie.weatherModel.forecastData.ForecastData;
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

    public TodaysData getTodayData() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.open-meteo.com/v1/dwd-icon?latitude=51.3197&longitude=7.3392&hourly=temperature_2m&timezone=Europe/Berlin&forecast_days=1";
        return restTemplate.getForObject(apiUrl, TodaysData.class);
    }
}
