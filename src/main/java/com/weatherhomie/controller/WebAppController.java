package com.weatherhomie.controller;

import com.weatherhomie.models.cityModel.Cities;
import com.weatherhomie.models.cityModel.City;
import com.weatherhomie.models.weatherModel.forecastData.ForecastData;
import com.weatherhomie.services.CityService;
import com.weatherhomie.services.WeatherIconService;
import com.weatherhomie.services.WeatherService;
import com.weatherhomie.models.weatherModel.timeAndTempMaps.TimeTempMapToday;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Controller
public class WebAppController {

    private final WeatherService weatherService;
    private final CityService cityService;
    private final WeatherIconService weatherIconService;

    public WebAppController(@Qualifier("weatherService") WeatherService weatherService, CityService cityService, WeatherIconService weatherIconService) {
        this.weatherService = weatherService;
        this.cityService = cityService;
        this.weatherIconService = weatherIconService;
    }

    @GetMapping("/weather")
    public String weather(Model model) {
        String cityName = "Berlin";
        City city = cityService.getListOfCityObjByName(cityName).results().getFirst();
        return prepareWeatherModel(city.id(), model);
    }

    @GetMapping("/city/{stadt}")
    public String showCityDetails(@PathVariable int stadt, Model model) {
        return prepareWeatherModel(stadt, model);
    }

    @GetMapping("/search")
    public String searchCities(@RequestParam String q, Model model) {
        Cities cities = new Cities(cityService.getListOfCityObjByName(q).results());
        prepareSearchModel(cities, model);
        return "searchResultList";
    }

    private void prepareSearchModel(Cities cities, Model model) {
        model.addAttribute("cities", cities.results());
    }


    private String prepareWeatherModel(int cityId, Model model) {
        City city = cityService.getCityById(cityId);
        ForecastData forecastData = weatherService.getForecastForCity(city);
        TimeTempMapToday timeAndTempDayMap = weatherService.getDaysTempByCity(city);
        ZoneId zoneId = ZoneId.of(forecastData.timezone());
        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
        LocalDateTime localDateTime = currentTime.toLocalDateTime();
        String iconPath = weatherIconService.getWeatherIcon(cityService.getCityById(cityId));


        model.addAttribute("cityName", city.name());
        model.addAttribute("currentTime", localDateTime.toLocalTime().withNano(0).withSecond(0));
        model.addAttribute("currentTemp", timeAndTempDayMap.map().values().toArray()[0]);
        model.addAttribute("pageTitle", "Weather Forecast");
        model.addAttribute("timeList", forecastData.hourly().time());
        model.addAttribute("tempMap", timeAndTempDayMap);
        model.addAttribute("weatherIcon", iconPath);

        return "weather";
    }


}
