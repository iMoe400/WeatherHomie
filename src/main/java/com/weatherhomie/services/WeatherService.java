package com.weatherhomie.services;


import com.weatherhomie.controller.WeatherController;
import com.weatherhomie.weatherModel.TempList;
import com.weatherhomie.weatherModel.TimeList;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {


    WeatherController weatherController = new WeatherController();
    @Getter
    TimeList timeList = new TimeList(weatherController.getForecastData().hourly().time());
    @Getter
    TempList tempList = new TempList(weatherController.getForecastData().hourly().temperature_2m());


    public Map<LocalDateTime, Double> getTimeAndTemp(TimeList timeList, TempList tempList) {
        Map<LocalDateTime, Double> timeAndTempMap = new HashMap<>();
        for (int i = 0; i < timeList.getLength(); i++)
            timeAndTempMap.put(timeList.getTime(i), tempList.temperature_2m().get(i));
        return timeAndTempMap;
    }


    public void printTimeAndTemp() {

        for (int i = 0; i < timeList.getLength(); i++) {
            System.out.println(timeList.getTime(i).getDayOfMonth() + "  " + timeList.getTime(i).getHour() + ":00" + "  Celsius: " + tempList.temperature_2m().get(i).toString());
        }

    }
}









