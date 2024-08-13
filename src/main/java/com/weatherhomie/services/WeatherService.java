package com.weatherhomie.services;


import com.weatherhomie.models.cityModel.City;
import com.weatherhomie.models.weatherModel.forecastData.ForecastData;
import com.weatherhomie.models.weatherModel.forecastData.TempList;
import com.weatherhomie.models.weatherModel.forecastData.TimeList;
import com.weatherhomie.models.weatherModel.timeAndTempMaps.TimeTempMapToday;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.KeyStore;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class WeatherService {


    public ForecastData getForecastForCity(City city) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                + city.latitude()
                + "&longitude="
                + city.longitude()
                + "&timezone="
                + city.timezone()
                + "&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,"
                + "precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,"
                + "surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m"
                + "&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,"
                + "precipitation,rain,showers,snowfall,snow_depth,weather_code,pressure_msl,"
                + "surface_pressure,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,"
                + "evapotranspiration,et0_fao_evapotranspiration,vapour_pressure_deficit,wind_speed_10m,"
                + "wind_speed_80m,wind_speed_120m,wind_speed_180m,wind_direction_10m,wind_direction_80m,"
                + "wind_direction_120m,wind_direction_180m,wind_gusts_10m,temperature_80m,temperature_120m,"
                + "temperature_180m,soil_temperature_0cm,soil_temperature_6cm,soil_temperature_18cm,"
                + "soil_temperature_54cm,soil_moisture_0_to_1cm,soil_moisture_1_to_3cm,soil_moisture_3_to_9cm,"
                + "soil_moisture_9_to_27cm,soil_moisture_27_to_81cm"
                + "&daily=weather_code,temperature_2m_max,temperature_2m_min,apparent_temperature_max,"
                + "apparent_temperature_min,sunrise,sunset,daylight_duration,sunshine_duration,"
                + "precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_hours,"
                + "precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,"
                + "wind_direction_10m_dominant,shortwave_radiation_sum,et0_fao_evapotranspiration"
                + "&models=icon_seamless&forecast_days=10";

        return restTemplate.getForObject(apiUrl, ForecastData.class);

    }


    public TimeTempMapToday getDaysTempByCity(City city) {
        ForecastData forecastData = getForecastForCity(city);
        TimeList timeList = new TimeList(getForecastForCity(city).hourly().time());
        TempList tempList = new TempList(getForecastForCity(city).hourly().temperature_2m());
        int cnt = 0;
        System.out.println(city.timezone() + "2");
        Map<LocalDateTime, Double> timeAndTempMap = new LinkedHashMap<>();
        for (int i = 0; i < timeList.time().size(); i++) {
            ZoneId zoneId = ZoneId.of(forecastData.timezone());
            ZonedDateTime currentTime = ZonedDateTime.now(zoneId);

            if (timeList.time().get(i).isAfter(currentTime.toLocalDateTime()) && cnt < 24) {
                cnt += 1;
                LocalDateTime localDateTime = timeList.time().get(i);
                timeAndTempMap.put(localDateTime, tempList.temperature_2m().get(i));
            }
        }
        return new TimeTempMapToday(timeAndTempMap);
    }

    public int getCurrentWeatherCode(City city) {
        ForecastData forecastData = getForecastForCity(city);
        return forecastData.hourly().weatherCode().getFirst();
    }

    public boolean isDay(City city) {
        ForecastData forecastData = getForecastForCity(city);
        int isDay = forecastData.current().isDay();
        return isDay != 0;
    }

    public Map<String, Double> get10DaysForecastByCity(City city) {
        Map<String, Double> mapson = new LinkedHashMap<>();
        DecimalFormat decimalFormat = new DecimalFormat("#");
        ForecastData forecastData = getForecastForCity(city);
        List<Double> maxTemp9Days = getForecastForCity(city).daily().temperature2mMax();
        List<Double> minTemp9Days = getForecastForCity(city).daily().temperature2mMin();
        Double avg;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM");
        List<LocalDate> date = getForecastForCity(city).daily().time();
        for (int i = 0; i < 9; i++) {
            avg = maxTemp9Days.get(i);
            if(i != 0 && avg != null){
                mapson.put(date.get(i).format(dateTimeFormatter), Double.parseDouble(decimalFormat.format(avg)));
            }

        }



        return mapson;
    }

}









