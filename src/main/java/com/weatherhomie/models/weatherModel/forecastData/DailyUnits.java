package com.weatherhomie.models.weatherModel.forecastData;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DailyUnits(
                         @JsonProperty("weather_code") String weatherCode,
                         @JsonProperty("temperature_2m_max") String temperature2mMax,
                         @JsonProperty("temperature_2m_min") String temperature2mMin,
                         @JsonProperty("apparent_temperature_max") String apparentTemperatureMax,
                         @JsonProperty("apparent_temperature_min") String apparentTemperatureMin,
                         String sunrise,
                         String sunset,
                         @JsonProperty("daylight_duration") String daylightDuration,
                         @JsonProperty("sunshine_duration") String sunshineDuration,
                         @JsonProperty("precipitation_sum") String precipitationSum,
                         @JsonProperty("rain_sum") String rainSum,
                         @JsonProperty("showers_sum") String showersSum,
                         @JsonProperty("snowfall_sum") String snowfallSum,
                         @JsonProperty("precipitation_hours") String precipitationHours,
                         @JsonProperty("precipitation_probability_max") String precipitationProbabilityMax,
                         @JsonProperty("wind_speed_10m_max") String windSpeed10mMax,
                         @JsonProperty("wind_gusts_10m_max") String windGusts10mMax,
                         @JsonProperty("wind_direction_10m_dominant") String windDirection10mDominant,
                         @JsonProperty("shortwave_radiation_sum") String shortwaveRadiationSum,
                         @JsonProperty("et0_fao_evapotranspiration") String et0FaoEvapotranspiration) {
}
