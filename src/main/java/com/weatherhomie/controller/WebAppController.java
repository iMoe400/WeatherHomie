package com.weatherhomie.controller;

import com.weatherhomie.services.WeatherService;
import com.weatherhomie.weatherModel.timeAndTempMaps.TimeTempMapToday;
import com.weatherhomie.weatherModel.forecastData.TimeList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebAppController {


    private final WeatherService weatherService;

    public WebAppController(@Qualifier("weatherService") WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    public String forecast(Model model) {

        LocalDateTime currentTime = LocalDateTime.now().withNano(0).withSecond(0);
        TimeList timeList = weatherService.getTimeList();
        TimeTempMapToday timeAndTempDayMap = weatherService.getTimeAndTempDay();
        LocalDateTime dateTime = LocalDateTime.now().withNano(0).withSecond(0).withMinute(0);
        System.out.println();
        System.out.println(dateTime);

        //Capital Time/Temp
        model.addAttribute("currentTime", currentTime);
        model.addAttribute("currentTemp", weatherService.getCurrentHourTemp());
        model.addAttribute("pageTitle", "Weather Forecast");
        model.addAttribute("timeList", timeList);
        model.addAttribute("tempMap", timeAndTempDayMap);

        //Next Hours forecast


        //WeatherMap


        return "forecast";
    }

}
