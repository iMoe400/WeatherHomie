package com.weatherhomie.models.weatherModel.timeAndTempMaps;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public record TimeTempMapToday(Map<LocalDateTime, Double> map) {

}
