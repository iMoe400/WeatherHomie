package com.weatherhomie.services;

import com.weatherhomie.models.cityModel.Cities;
import com.weatherhomie.models.cityModel.City;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityService {

    public Cities getListOfCityObjByName(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=10&language=en&format=json";
        return restTemplate.getForObject(apiUrl, Cities.class);
    }

    public City getCityById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://geocoding-api.open-meteo.com/v1/get?id=" + id;
        return restTemplate.getForObject(apiUrl, City.class);
    }
}
