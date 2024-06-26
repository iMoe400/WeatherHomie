package com.weatherhomie.weatherModel.forecastData;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record HourlyData(List<LocalDateTime> time, List<Double> temperature_2m) {
    @Override
    public List<LocalDateTime> time() {
        return time;
    }

    public List<Double> temperature_2m() {
        return temperature_2m;
    }

}


