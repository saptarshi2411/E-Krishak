package com.example.JwtWithMongo;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "external_api")
public class ExternalApi {

    private String key;
    private String value;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

