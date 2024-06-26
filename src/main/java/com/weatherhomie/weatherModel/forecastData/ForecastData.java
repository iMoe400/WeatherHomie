package com.weatherhomie.weatherModel.forecastData;

public record ForecastData( double latitude,
                            double longitude,
                            double generationTime_ms,
                            int utc_offset_seconds,
                            String timezone,
                            String timezone_abbreviation,
                            double elevation,
                            HourlyData hourly) {
}
