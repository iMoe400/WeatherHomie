package com.weatherhomie.controller;

import com.weatherhomie.services.WeatherService;
import com.weatherhomie.weatherModel.TempList;
import com.weatherhomie.weatherModel.TimeAndTemp;
import com.weatherhomie.weatherModel.TimeList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class WebAppController {


    private final WeatherService weatherService;

    public WebAppController(@Qualifier("weatherService") WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    public String forecast(Model model) {
        // Hier können Daten für die Forecast-Seite hinzugefügt werden
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime currentHour = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        System.out.println(now);
        TempList temp = new TempList(weatherService.getTempList().temperature_2m());
        TimeList time = new TimeList(weatherService.getTimeList().time());
        TimeAndTemp all = new TimeAndTemp(weatherService.getTimeAndTemp(time, temp));

        model.addAttribute("currentTime", now);
        model.addAttribute("currentTemp", all.timeAndTemp().get(currentHour));
        model.addAttribute("pageTitle", "Weather Forecast");
        return "forecast"; // Gibt den Namen des HTML-Templates zurück
    }

}
