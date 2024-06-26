package com.weatherhomie.services;


import com.weatherhomie.controller.WeatherController;
import com.weatherhomie.weatherModel.TodaysData;
import com.weatherhomie.weatherModel.forecastData.TempList;
import com.weatherhomie.weatherModel.forecastData.TimeList;
import com.weatherhomie.weatherModel.timeAndTempMaps.TimeTempMapToday;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class WeatherService {


    WeatherController weatherController = new WeatherController();

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public double getCurrentHourTemp() {
        double currentHourTemp = 0;
        TodaysData todayData = weatherController.getTodayData();
        TempList tempList = new TempList(todayData.hourly().temperature_2m());
        TimeList timeList = new TimeList(todayData.hourly().time());
        for(int i = 0; i < tempList.temperature_2m().size(); i++) {
            if (timeList.getTime(i).toLocalTime().equals(LocalTime.now().withMinute(0).withSecond(0).withNano(0))) {
                currentHourTemp = tempList.temperature_2m().get(i);
            }
        }

        return currentHourTemp;
    }

    public TempList getTempList() {

        return new TempList(weatherController.getForecastData().hourly().temperature_2m());

    }

    public TimeList getTimeList() {

        return new TimeList(weatherController.getForecastData().hourly().time());

    }


    public TimeTempMapToday getTimeAndTempDay() {
        TimeList timeList = getTimeList();
        TempList tempList = getTempList();
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

}









