package com.weatherhomie.services;


import com.weatherhomie.models.cityModel.City;
import com.weatherhomie.models.weatherModel.forecastData.ForecastData;
import com.weatherhomie.models.weatherModel.forecastData.TempList;
import com.weatherhomie.models.weatherModel.forecastData.TimeList;
import com.weatherhomie.models.weatherModel.timeAndTempMaps.TimeTempMapToday;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.util.*;

@Service
public class WeatherService {


    public ForecastData getForecastForCity(City city) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                + city.latitude()
                + "&longitude="
                + city.longitude()
                + "&hourly=temperature_2m&timezone="
                + city.timezone();
        System.out.println(city.timezone() + city.latitude() + "   " + city.longitude());
        return (restTemplate.getForObject(apiUrl, ForecastData.class));
    }


    public TimeTempMapToday getDaysTempByCity(City city) {
        ForecastData forecastData = getForecastForCity(city);
        TimeList timeList = new TimeList(getForecastForCity(city).hourly().time());
        TempList tempList = new TempList(getForecastForCity(city).hourly().temperature_2m());
        int cnt = 0;
        System.out.println(city.timezone() + "2");
        Map<LocalDateTime, Double> timeAndTempMap = new LinkedHashMap<>();
        for (int i = 0; i < timeList.time().size(); i++) {
            ZoneId zoneId = ZoneId.of(forecastData.timezone());
            ZonedDateTime currentTime = ZonedDateTime.now(zoneId);

            if (timeList.time().get(i).isAfter(currentTime.toLocalDateTime()) && cnt < 24) {
                cnt += 1;
                LocalDateTime localDateTime = timeList.time().get(i);
                timeAndTempMap.put(localDateTime, tempList.temperature_2m().get(i));
            }
        }
        return new TimeTempMapToday(timeAndTempMap);
    }


}









