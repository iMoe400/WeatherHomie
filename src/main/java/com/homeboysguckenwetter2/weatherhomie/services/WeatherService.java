package com.homeboysguckenwetter2.weatherhomie.services;


import com.homeboysguckenwetter2.weatherhomie.controller.WeatherController;
import com.homeboysguckenwetter2.weatherhomie.weatherModel.TempList;
import com.homeboysguckenwetter2.weatherhomie.weatherModel.TimeList;
import com.homeboysguckenwetter2.weatherhomie.weatherModel.TimeAndTemp;


import java.util.HashMap;
import java.util.Map;

public class WeatherService {
    WeatherController weatherController = new WeatherController();
    TimeList timeList = new TimeList(weatherController.getForecastData().hourly().time());
    TempList tempList = new TempList(weatherController.getForecastData().hourly().temperature_2m());

    public WeatherService() {
    }

    public TimeAndTemp getTimeAndTemp() {
        Map<TimeList, TempList> timeTempMap = new HashMap<>();
        timeTempMap.put(timeList, tempList);
        return new TimeAndTemp(timeTempMap);
    }

    public void printTimeAndTemp() {

        for (int i = 0; i < timeList.getLength(); i++) {
            System.out.println(timeList.getTime(i).getDayOfMonth() + "  " + timeList.getTime(i).getHour() + ":00" + "  Celsius: " + tempList.temperature_2m().get(i).toString());
        }

    }
}









