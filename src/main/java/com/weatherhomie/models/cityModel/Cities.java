package com.weatherhomie.models.cityModel;

import java.util.List;

public record Cities(
        int id,
        String name,
        double latitude,
        double longitude,
        double elevation,
        String feature_code,
        String country_code,
        int admin1_id,
        int admin2_id,
        int admin3_id,
        int admin4_id,
        String timezone,
        int population,
        List<String> postcodes,
        int country_id,
        String country,
        String admin1,
        String admin2,
        String admin3,
        String admin4) {
}
