package com.homeboysguckenwetter2.weatherhomie.weatherModel;

import java.util.List;

public record HourlyData(List<String> time, List<Double> temperature_2m) {
}


