package com.example.JwtWithMongo;

import com.example.JwtWithMongo.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<Users, ObjectId> {
    Users findByUsername(String username);


    @Query("{ 'products' : ?0 }")
    List<Users> findByProductsContains(Product product);


    @Query("{ 'products' : ?0 }")
    List<Users> findByBlogsContaining(Blog blog);


}
