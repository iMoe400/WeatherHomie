package com.weatherhomie.models.weatherModel.forecastData;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ForecastData(double latitude,
                           double longitude,
                           @JsonProperty("generationtime_ms") double generationTimeMs,
                           @JsonProperty("utc_offset_seconds") int utcOffsetSeconds,
                           String timezone,
                           @JsonProperty("timezone_abbreviation") String timezoneAbbreviation,
                           double elevation,
                           @JsonProperty("current_units") CurrentUnits currentUnits,
                           Current current,
                           @JsonProperty("hourly_units") HourlyUnits hourlyUnits,
                           Hourly hourly,
                           @JsonProperty("daily_units") DailyUnits dailyUnits,
                           Daily daily
                           ) {


}
