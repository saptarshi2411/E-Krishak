package com.example.JwtWithMongo;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepo extends MongoRepository<WishList, ObjectId> {

    Optional<Product> findByRandomId(int randomId);
}