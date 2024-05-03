package com.homeboysguckenwetter2.weatherhomie.weatherModel;

import java.util.Map;

public record TimeAndTemp(Map<TimeList, TempList> timeTempMap) {
}
