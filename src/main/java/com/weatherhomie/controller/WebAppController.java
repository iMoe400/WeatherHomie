package com.weatherhomie.controller;

import com.weatherhomie.endpoint.CItyEndpoint;
import com.weatherhomie.endpoint.WeatherEndpoint;
import com.weatherhomie.models.weatherModel.forecastData.TempList;
import com.weatherhomie.services.WeatherService;
import com.weatherhomie.models.weatherModel.timeAndTempMaps.TimeTempMapToday;
import com.weatherhomie.models.weatherModel.forecastData.TimeList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Controller
public class WebAppController {

    private final WeatherService weatherService;
    private final com.weatherhomie.endpoint.CItyEndpoint CItyEndpoint;
    private final WeatherEndpoint weatherEndpoint;

    public WebAppController(@Qualifier("weatherService") WeatherService weatherService, @Qualifier("CItyEndpoint") CItyEndpoint CItyEndpoint, WeatherEndpoint weatherEndpoint) {
        this.weatherService = weatherService;
        this.CItyEndpoint = CItyEndpoint;
        this.weatherEndpoint = weatherEndpoint;
    }


    LocalDateTime currentTime = LocalDateTime.now().withNano(0).withSecond(0);


    @GetMapping("/weather")
    public String weather(Model model) {

        TimeList timeList = weatherService.getTimeList();
        TimeTempMapToday timeAndTempDayMap = weatherService.getDaysTempByCity(weatherEndpoint.getCityById(CItyEndpoint.getCityLatLong("Berlin").results().getFirst().id()));
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

    @GetMapping("/city/{stadt}")
    public String showCityDetails(@PathVariable String stadt, Model model) {
        String cityNam = stadt;
        String currentTemp = weatherService.getCurrentHourTempByCity(weatherEndpoint.getCityById(CItyEndpoint.getCityLatLong(stadt).results().getFirst().id()));
        TimeList timeList = weatherService.getTimeList();
        TimeTempMapToday timeAndTempDayMap = weatherService.getDaysTempByCity(weatherEndpoint.getCityById(CItyEndpoint.getCityLatLong(stadt).results().getFirst().id()));
        LocalDateTime dateTime = LocalDateTime.now().withNano(0).withSecond(0).withMinute(0);
        System.out.println(timeAndTempDayMap);
        System.out.println("du nutte");
        System.out.println(dateTime);
        TimeTempMapToday timeAndTemp = new TimeTempMapToday(weatherService.getDaysTempByCity(weatherEndpoint.getCityById(CItyEndpoint.getCityLatLong(stadt).results().getFirst().id())).map());
        model.addAttribute("currentCityName", cityNam);
        model.addAttribute("currentTime", currentTime);
        model.addAttribute("currentTemp", currentTemp);
        model.addAttribute("pageTitle", "Weather Forecast");
        model.addAttribute("timeList", timeList);
        model.addAttribute("tempMap", timeAndTempDayMap);
        return "weather";
    }

}
