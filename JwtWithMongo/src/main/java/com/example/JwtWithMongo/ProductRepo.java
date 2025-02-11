package com.example.JwtWithMongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends MongoRepository<Product, ObjectId> {
    Optional<Product> findByProdId(int prodId);
    void deleteByProdId(int prodId);

    List<Product> findByCategory(String category);
}
