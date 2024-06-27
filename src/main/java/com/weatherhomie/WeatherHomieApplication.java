package com.weatherhomie;


import com.weatherhomie.controller.CityController;
import com.weatherhomie.controller.WeatherController;
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
    public CommandLineRunner commandLineRunner(WeatherController weatherController) {
        return args -> {
            CityController cityController = new CityController(weatherController);
            System.out.println(cityController.searchCityByName("Berlin"));
        };
    }

}
