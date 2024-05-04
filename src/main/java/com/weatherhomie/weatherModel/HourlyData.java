package com.weatherhomie.weatherModel;

import java.time.LocalDateTime;
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


