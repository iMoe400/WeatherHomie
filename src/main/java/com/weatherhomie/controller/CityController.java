package com.weatherhomie.controller;

import com.weatherhomie.models.cityModel.ListOfCitys;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class CityController {
    public CityController(WeatherController weatherController){

    }
    @GetMapping("/search/cityLatLong")
    public ListOfCitys getCityLatLong(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=10&language=en&format=json";
        return restTemplate.getForObject(apiUrl, ListOfCitys.class);
    }

    public ListOfCitys searchCityByName(String string) {
        return getCityLatLong(string);
    }

}
