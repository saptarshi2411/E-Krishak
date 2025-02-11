package com.example.JwtWithMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@CrossOrigin
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public Weather getWeather(@RequestParam String city) {
        return weatherService.findWeather(city);
    }
}


