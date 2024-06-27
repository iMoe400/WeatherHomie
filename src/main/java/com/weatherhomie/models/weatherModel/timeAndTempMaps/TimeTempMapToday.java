package com.weatherhomie.models.weatherModel.timeAndTempMaps;

import java.time.LocalDateTime;

import java.util.Map;

public record TimeTempMapToday(Map<LocalDateTime, Double> map) {

}
