package com.homeboysguckenwetter2.weatherhomie;

import com.homeboysguckenwetter2.weatherhomie.services.WeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherHomieApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherHomieApplication.class, args);
    }


    @Bean
    public WeatherService weatherService() {
        return new WeatherService();
    }

    @Bean
    public CommandLineRunner commandLineRunner(WeatherService weatherService) {
        return args -> {
            System.out.println(weatherService.getForecastData());
        };
    }

}
