package com.example.JwtWithMongo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ExternalApiRepo externalApiRepo;

    private Map<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ExternalApi> all = externalApiRepo.findAll();
        for (ExternalApi config : all) {
            appCache.put(config.getKey(), config.getValue());
        }
    }

    public Map<String, String> getAppCache() {
        return appCache;
    }
}
