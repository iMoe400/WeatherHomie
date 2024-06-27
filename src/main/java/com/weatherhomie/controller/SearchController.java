package com.weatherhomie.controller;

import com.weatherhomie.models.cityModel.Cities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final CityController cityController;

    @Autowired
    public SearchController(CityController cityController) {
        this.cityController = cityController;
    }

    @GetMapping("/search")
    public String searchCities(@RequestParam String q, Model model) {
        List<Cities> cities = cityController.searchCityByName(q).results();
        model.addAttribute("cities", cities);
        return "searchResultList"; // Thymeleaf fragment name
    }

}