package com.example.JwtWithMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;


    @Value("${weather.api.key}")
    private String apikey;

    public Weather findWeather(String city) {

        String finalApi = appCache.getAppCache().get("weather_api")
                .replace("<city>", city)
                .replace("<apikey>", apikey);


        ResponseEntity<Weather> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, Weather.class);
        return response.getBody();
    }
}

