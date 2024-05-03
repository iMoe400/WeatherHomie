package com.homeboysguckenwetter2.weatherhomie.weatherModel;

import java.util.Map;

public record TimeAndTemp(Map<Time, Temp> timeTempMap) {
}
