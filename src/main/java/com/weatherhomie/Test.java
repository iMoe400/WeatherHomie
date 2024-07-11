package com.weatherhomie;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weatherhomie.endpoint.CItyEndpoint;
import com.weatherhomie.endpoint.WeatherEndpoint;
import com.weatherhomie.models.weatherModel.forecastData.TimeList;
import com.weatherhomie.models.weatherModel.timeAndTempMaps.TimeTempMapToday;
import com.weatherhomie.services.WeatherService;

@Controller
public class Test {

public Test(@Qualifier("weatherService") WeatherService weatherService, @Qualifier("CItyEndpoint") CItyEndpoint cItyEndpoint, WeatherEndpoint weatherEndpoint) {
        this.weatherService = weatherService;
        this.cItyEndpoint = cItyEndpoint;
        this.weatherEndpoint = weatherEndpoint;
    }

    LocalDateTime currentTime = LocalDateTime.now().withNano(0).withSecond(0);

    WeatherService weatherService = new WeatherService();
    WeatherEndpoint weatherEndpoint = new WeatherEndpoint();
    CItyEndpoint cItyEndpoint = new CItyEndpoint(weatherEndpoint);
    @GetMapping("/test")
    public String test(Model model){
        TimeList timeList = weatherService.getTimeList();
        TimeTempMapToday timeAndTempDayMap = weatherService.getDaysTempByCity(weatherEndpoint.getCityById(cItyEndpoint.getCityLatLong("Berlin").results().getFirst().id()));
        LocalDateTime dateTime = LocalDateTime.now().withNano(0).withSecond(0).withMinute(0);
        System.out.println();
        System.out.println(timeAndTempDayMap);
        System.out.println(dateTime);

        //Capital Time/Temp
        model.addAttribute("cityName", "Berlin");
        model.addAttribute("currentTime", currentTime);
        model.addAttribute("currentTemp", weatherService.getCurrentHourTemp());
        model.addAttribute("pageTitle", "Weather Forecast");
        model.addAttribute("timeList", timeList);
        model.addAttribute("tempMap", timeAndTempDayMap);


        return "weather";
    }
}
