package com.weatherhomie.models.weatherModel.forecastData;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import java.util.List;

public record Hourly(List<LocalDateTime> time,
                     @JsonProperty("temperature_2m") List<Double> temperature2m,
                     @JsonProperty("relative_humidity_2m") List<Integer> relativeHumidity2m,
                     @JsonProperty("dew_point_2m") List<Double> dewPoint2m,
                     @JsonProperty("apparent_temperature") List<Double> apparentTemperature,
                     List<Double> precipitation,
                     List<Double> rain,
                     List<Double> showers,
                     List<Double> snowfall,
                     @JsonProperty("snow_depth") List<Double> snowDepth,
                     @JsonProperty("weather_code") List<Integer> weatherCode,
                     @JsonProperty("pressure_msl") List<Double> pressureMsl,
                     @JsonProperty("surface_pressure") List<Double> surfacePressure,
                     @JsonProperty("cloud_cover") List<Integer> cloudCover,
                     @JsonProperty("cloud_cover_low") List<Integer> cloudCoverLow,
                     @JsonProperty("cloud_cover_mid") List<Integer> cloudCoverMid,
                     @JsonProperty("cloud_cover_high") List<Integer> cloudCoverHigh,
                     List<Double> evapotranspiration,
                     @JsonProperty("et0_fao_evapotranspiration") List<Double> et0FaoEvapotranspiration,
                     @JsonProperty("vapour_pressure_deficit") List<Double> vapourPressureDeficit,
                     @JsonProperty("wind_speed_10m") List<Double> windSpeed10m,
                     @JsonProperty("wind_speed_80m") List<Double> windSpeed80m,
                     @JsonProperty("wind_speed_120m") List<Double> windSpeed120m,
                     @JsonProperty("wind_speed_180m") List<Double> windSpeed180m,
                     @JsonProperty("wind_direction_10m") List<Integer> windDirection10m,
                     @JsonProperty("wind_direction_80m") List<Integer> windDirection80m,
                     @JsonProperty("wind_direction_120m") List<Integer> windDirection120m,
                     @JsonProperty("wind_direction_180m") List<Integer> windDirection180m,
                     @JsonProperty("wind_gusts_10m") List<Double> windGusts10m,
                     @JsonProperty("temperature_80m") List<Double> temperature80m,
                     @JsonProperty("temperature_120m") List<Double> temperature120m,
                     @JsonProperty("temperature_180m") List<Double> temperature180m,
                     @JsonProperty("soil_temperature_0cm") List<Double> soilTemperature0cm,
                     @JsonProperty("soil_temperature_6cm") List<Double> soilTemperature6cm,
                     @JsonProperty("soil_temperature_18cm") List<Double> soilTemperature18cm,
                     @JsonProperty("soil_temperature_54cm") List<Double> soilTemperature54cm,
                     @JsonProperty("soil_moisture_0_to_1cm") List<Double> soilMoisture0To1cm,
                     @JsonProperty("soil_moisture_1_to_3cm") List<Double> soilMoisture1To3cm,
                     @JsonProperty("soil_moisture_3_to_9cm") List<Double> soilMoisture3To9cm,
                     @JsonProperty("soil_moisture_9_to_27cm") List<Double> soilMoisture9To27cm,
                     @JsonProperty("soil_moisture_27_to_81cm") List<Double> soilMoisture27To81cm) {
    @Override
    public List<LocalDateTime> time() {
        return time;
    }

    public List<Double> temperature_2m() {
        return temperature2m;
    }

}


