package com.homeboysguckenwetter2.weatherhomie.services;


import com.homeboysguckenwetter2.weatherhomie.controller.WeatherController;
import com.homeboysguckenwetter2.weatherhomie.weatherModel.TempList;
import com.homeboysguckenwetter2.weatherhomie.weatherModel.TimeList;
import com.homeboysguckenwetter2.weatherhomie.weatherModel.TimeAndTemp;

import java.util.HashMap;
import java.util.Map;

public class WeatherService {

    public WeatherService() {
    }

    public TimeAndTemp getTimeAndTemp() {
        WeatherController weatherController = new WeatherController();
        TempList temps = new TempList(weatherController.getForecastData().hourly().temperature_2m());
        TimeList times = new TimeList(weatherController.getForecastData().hourly().time());
        Map<TimeList, TempList> timeTempMap = new HashMap<>();


        timeTempMap.put(times, temps);

        return new TimeAndTemp(timeTempMap);
    }

    public void printTimeAndTemp() {
        WeatherController weatherController = new WeatherController();
        TimeList timelist = new TimeList(weatherController.getForecastData().hourly().time());
        TimeAndTemp timeAndTemp = getTimeAndTemp();


        for (int i = 0; i < timeAndTemp.timeTempMap().get(timelist).temperature_2m().size(); i++) {
            System.out.println(
                    timelist.getTime(i).getDayOfMonth() + "  "
                            + timelist.getTime(i).getHour()
                            + ":00" + "  Celsius: "
                            + timeAndTemp.timeTempMap().get(timelist).temperature_2m().get(i).toString());
        }
    }
}









