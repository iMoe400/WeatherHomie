package com.weatherhomie.weatherModel;

import java.time.LocalDateTime;
import java.util.Map;

public record TimeAndTemp(Map<LocalDateTime, Double> timeAndTemp) {

}
