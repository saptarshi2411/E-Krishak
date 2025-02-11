package com.example.JwtWithMongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "mandiPrice")
public class MandiPrice {


    @Id
    private ObjectId id;
    private int price;
    private String category;
    private String place;


    public MandiPrice(int price, String category, String place,ObjectId id) {
        this.id=id;
        this.price = price;
        this.category = category;
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
