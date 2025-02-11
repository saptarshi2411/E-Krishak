package com.example.JwtWithMongo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;


    @Value("${news.api.key}")
    private String apikey;

    public News findNews() {
        // Retrieve the API URL from the cache and replace placeholders
        String finalApi = appCache.getAppCache().get("news_api")
                .replace("<apikey>", apikey);


        ResponseEntity<News> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, News.class);
        return response.getBody();
    }
}
