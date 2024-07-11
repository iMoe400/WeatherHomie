package com.weatherhomie.services;


import com.weatherhomie.endpoint.WeatherEndpoint;
import com.weatherhomie.models.cityModel.City;
import com.weatherhomie.models.weatherModel.TodaysData;
import com.weatherhomie.models.weatherModel.forecastData.TempList;
import com.weatherhomie.models.weatherModel.forecastData.TimeList;
import com.weatherhomie.models.weatherModel.timeAndTempMaps.TimeTempMapToday;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class WeatherService {


    WeatherEndpoint weatherEndpoint = new WeatherEndpoint();



    public double getCurrentHourTemp() {
        double currentHourTemp = 0;
        TodaysData todayData = weatherEndpoint.getTodayData();
        TempList tempList = new TempList(todayData.hourly().temperature_2m());
        TimeList timeList = new TimeList(todayData.hourly().time());
        for(int i = 0; i < tempList.temperature_2m().size(); i++) {
            if (timeList.getTime(i).toLocalTime().equals(LocalTime.now().withMinute(0).withSecond(0).withNano(0))) {
                currentHourTemp = tempList.temperature_2m().get(i);
                System.out.println(currentHourTemp);
            }
        }

        return currentHourTemp;
    }

    public TimeList getTimeList() {

        return new TimeList(weatherEndpoint.getForecastData().hourly().time());

    }


    public TimeTempMapToday getDaysTempByCity(City city) {
        TimeList timeList = new TimeList(weatherEndpoint.getForecastForCity(city).hourly().time());
        TempList tempList = new TempList(weatherEndpoint.getForecastForCity(city).hourly().temperature_2m());
        int countAday = 0;
        Map<LocalDateTime, Double> timeAndTempMap = new LinkedHashMap<>();
        for (int i = 0; i < timeList.time().size(); i++) {
            LocalDateTime time = timeList.time().get(i).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime now = LocalDateTime.now();

            if(time.isAfter(now) && countAday < 24){
                countAday++;
                timeAndTempMap.put(time.withNano(0).withSecond(0), tempList.temperature_2m().get(i));
            }
        }
        return new TimeTempMapToday(timeAndTempMap);
    }

    public String getCurrentHourTempByCity(City city) {
        TempList tempList = new TempList(weatherEndpoint.getForecastForCity(city).hourly().temperature_2m());
        return tempList.temperature_2m().getFirst().toString();
    }



}









