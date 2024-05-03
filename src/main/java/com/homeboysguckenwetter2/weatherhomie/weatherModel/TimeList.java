package com.homeboysguckenwetter2.weatherhomie.weatherModel;

import java.time.LocalDateTime;
import java.util.List;

public record TimeList(List<LocalDateTime> time) {
    public LocalDateTime getTime(int i) {
        return time.get(i);
    }
    public int getLength() {
        return time.size();
    }
}
