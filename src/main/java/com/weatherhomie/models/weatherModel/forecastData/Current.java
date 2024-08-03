package com.weatherhomie.models.weatherModel.forecastData;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Current(String time,
                      int interval,
                      @JsonProperty("temperature_2m") double temperature2m,
                      @JsonProperty("relative_humidity_2m") int relativeHumidity2m,
                      @JsonProperty("apparent_temperature") double apparentTemperature,
                      @JsonProperty("is_day") int isDay,
                      double precipitation,
                      double rain,
                      double showers,
                      double snowfall,
                      @JsonProperty("weather_code") int weatherCode,
                      @JsonProperty("cloud_cover") int cloudCover,
                      @JsonProperty("pressure_msl") double pressureMsl,
                      @JsonProperty("surface_pressure") double surfacePressure,
                      @JsonProperty("wind_speed_10m") double windSpeed10m,
                      @JsonProperty("wind_direction_10m") int windDirection10m,
                      @JsonProperty("wind_gusts_10m") double windGusts10m) {
}
