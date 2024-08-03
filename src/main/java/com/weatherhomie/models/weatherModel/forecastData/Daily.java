package com.weatherhomie.models.weatherModel.forecastData;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record Daily(
                    @JsonProperty("weather_code") List<Integer> weatherCode,
                    @JsonProperty("temperature_2m_max") List<Double> temperature2mMax,
                    @JsonProperty("temperature_2m_min") List<Double> temperature2mMin,
                    @JsonProperty("apparent_temperature_max") List<Double> apparentTemperatureMax,
                    @JsonProperty("apparent_temperature_min") List<Double> apparentTemperatureMin,
                    List<LocalDateTime> sunrise,
                    List<LocalDateTime> sunset,
                    @JsonProperty("daylight_duration") List<Double> daylightDuration,
                    @JsonProperty("sunshine_duration") List<Double> sunshineDuration,
                    @JsonProperty("precipitation_sum") List<Double> precipitationSum,
                    @JsonProperty("rain_sum") List<Double> rainSum,
                    @JsonProperty("showers_sum") List<Double> showersSum,
                    @JsonProperty("snowfall_sum") List<Double> snowfallSum,
                    @JsonProperty("precipitation_hours") List<Double> precipitationHours,
                    @JsonProperty("precipitation_probability_max") List<Integer> precipitationProbabilityMax,
                    @JsonProperty("wind_speed_10m_max") List<Double> windSpeed10mMax,
                    @JsonProperty("wind_gusts_10m_max") List<Double> windGusts10mMax,
                    @JsonProperty("wind_direction_10m_dominant") List<Integer> windDirection10mDominant,
                    @JsonProperty("shortwave_radiation_sum") List<Double> shortwaveRadiationSum,
                    @JsonProperty("et0_fao_evapotranspiration") List<Double> et0FaoEvapotranspiration) {
}
