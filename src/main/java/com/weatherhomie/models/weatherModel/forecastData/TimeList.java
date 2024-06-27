package com.weatherhomie.models.weatherModel.forecastData;

import java.time.LocalDateTime;

import java.util.List;

public record TimeList(List<LocalDateTime> time) {

    public LocalDateTime getTime(int i) {
        return time.get(i);
    }
}
