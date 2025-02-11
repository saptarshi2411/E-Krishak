package com.example.JwtWithMongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class NewUserService {

    @Autowired
    UserRepo repo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void deleteById(ObjectId id) {
        repo.deleteById(id);
    }

    public Optional<Users> findById(ObjectId id) {
        return repo.findById(id);
    }

}
