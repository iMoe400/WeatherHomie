package com.weatherhomie.controller;

import com.weatherhomie.models.cityModel.Cities;
import com.weatherhomie.models.cityModel.City;
import com.weatherhomie.services.WeatherService;
import com.weatherhomie.models.weatherModel.timeAndTempMaps.TimeTempMapToday;
import com.weatherhomie.models.weatherModel.forecastData.TimeList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class WebAppController {


    private final WeatherService weatherService;
    private final CityController cityController;
    private final WeatherController weatherController;

    public WebAppController(@Qualifier("weatherService") WeatherService weatherService, @Qualifier("cityController") CityController cityController, WeatherController weatherController) {
        this.weatherService = weatherService;
        this.cityController = cityController;
        this.weatherController = weatherController;
    }

    LocalDateTime currentTime = LocalDateTime.now().withNano(0).withSecond(0);




    @GetMapping("/weather")
    public String weather(Model model) {

        TimeList timeList = weatherService.getTimeList();
        TimeTempMapToday timeAndTempDayMap = weatherService.getTimeAndTempDay(weatherController.getCityById(cityController.getCityLatLong("Berlin").results().getFirst().id()));
        LocalDateTime dateTime = LocalDateTime.now().withNano(0).withSecond(0).withMinute(0);
        System.out.println();
        System.out.println(timeAndTempDayMap);
        System.out.println("du nutte");
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
        TimeList timeList = weatherService.getTimeList();
        TimeTempMapToday timeAndTempDayMap = weatherService.getTimeAndTempDay(weatherController.getCityById(cityController.getCityLatLong(stadt).results().getFirst().id()));
        LocalDateTime dateTime = LocalDateTime.now().withNano(0).withSecond(0).withMinute(0);
        System.out.println(timeAndTempDayMap);
        System.out.println("du nutte");
        System.out.println(dateTime);
        TimeTempMapToday timeAndTemp = new TimeTempMapToday(weatherService.getTimeAndTempDay(weatherController.getCityById(cityController.getCityLatLong(stadt).results().getFirst().id())).map());
        model.addAttribute("currentCityName", cityNam);
        model.addAttribute("currentTime", currentTime);
        model.addAttribute("currentTemp", weatherService.getCurrentHourTemp());
        model.addAttribute("pageTitle", "Weather Forecast");
        model.addAttribute("timeList", timeList);
        model.addAttribute("tempMap", timeAndTempDayMap);
        return "weather";
    }

}
