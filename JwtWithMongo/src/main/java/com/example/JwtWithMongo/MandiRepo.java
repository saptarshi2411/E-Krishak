package com.example.JwtWithMongo;

import com.example.JwtWithMongo.Product;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MandiRepo extends MongoRepository<MandiPrice, ObjectId> {


    List<MandiPrice> findByCategory(String category);

    List<MandiPrice> findByPlace(String place);
}
