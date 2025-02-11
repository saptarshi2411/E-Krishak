package com.example.JwtWithMongo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishList")
public class WishList {

    @Id
    private String id;

    private int randomId;

    @DBRef
    private List<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getRandomId() {
        return randomId;
    }

    public void setRandomId(int randomId) {
        this.randomId = randomId;
    }
}

