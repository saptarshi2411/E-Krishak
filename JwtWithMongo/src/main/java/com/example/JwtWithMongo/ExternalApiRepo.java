package com.example.JwtWithMongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalApiRepo extends MongoRepository<ExternalApi, ObjectId> {
}
