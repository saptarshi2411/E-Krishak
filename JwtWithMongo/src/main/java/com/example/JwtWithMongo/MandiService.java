package com.example.JwtWithMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MandiService {

    @Autowired
    MandiRepo repo;
    public List<MandiPrice> getAll() {
        return repo.findAll();
    }

    public List<MandiPrice> getCategoryProducts(String category) {
        return repo.findByCategory(category); // Directly return the result of findByCategory
    }

    public List<MandiPrice> getPlace(String mandiPlace) {
        return repo.findByPlace(mandiPlace); // Directly return the result of findByCategory
    }

    public MandiPrice addMandiPrice(MandiPrice mandiPrice) {
        return repo.save(mandiPrice);
    }

}
