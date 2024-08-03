package com.weatherhomie.services;

import com.weatherhomie.models.cityModel.City;
import org.springframework.stereotype.Service;

@Service
public class WeatherIconService {

    private final WeatherService weatherService;

    WeatherIconService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    private static final String ICON_BASE_PATH = "/reshotIcons/";

    public String getWeatherIcon(City city) {
        int weatherCode = weatherService.getCurrentWeatherCode(city);
        String iconFilename = switch (weatherCode) {
            case 0 ->
                    weatherService.isDay(city) ? "reshot-icon-sunny-day-KUT6AVN2DH.svg" : "reshot-icon-moon-night-QPHXN87AVD.svg";
            case 1, 2, 3 ->
                    weatherService.isDay(city) ? "reshot-icon-cloud-sun-FZ9WMUH25G.svg" : "reshot-icon-cloud-moon-RTKBMAX968.svg";
            case 45, 48 -> "reshot-icon-cloud-forecast-P7U5KTHQY7.svg";
            case 51, 53, 55 -> "reshot-icon-rain-cloud-A5T8AQ23JC.svg";
            case 56, 57, 66, 67, 71, 73, 75, 77, 85, 86 -> "reshot-icon-snow-grains-H9F7X4MD9Z.svg";
            case 61, 63, 65 -> "reshot-icon-rain-rainy-BEJJPCQRA4.svg";
            case 80, 81, 82 -> "reshot-icon-rain-cloud-C77DXL9WEU.svg";
            case 95, 96, 99 -> "reshot-icon-cloud-thunder-FH4EA3J28N.svg";
            default -> "default.svg";
        };
        // Default icon if none matches
        System.out.println(ICON_BASE_PATH + iconFilename);
        return ICON_BASE_PATH + iconFilename;
    }

}
