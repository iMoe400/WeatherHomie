package com.weatherhomie.weatherModel.forecastData;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record TimeList(List<LocalDateTime> time) {

    public LocalDateTime getTime(int i) {
        return time.get(i);
    }

    public int getLength() {
        return time.size();
    }
}
