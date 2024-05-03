package com.homeboysguckenwetter2.weatherhomie.weatherModel;

import java.time.LocalDateTime;
import java.util.List;

public record HourlyData(List<LocalDateTime> time, List<Double> temperature_2m) {
}


