package com.example.JwtWithMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private JWTService jwtservice;



    @Autowired
    private EmailService emailService;


    @Autowired
    private UserRepo repo;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        user.setProducts(new ArrayList<>());
        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            Users authenticatedUser = repo.findByUsername(user.getUsername()); // Ensure you fetch the user from DB
            if (authenticatedUser != null && authenticatedUser.getRoles() != null) {
                return jwtservice.generateToken(authenticatedUser.getUsername(), authenticatedUser.getRoles());
            }
        }
        return "failure";
    }



    public Users findByUsername(String username) {
        return repo.findByUsername(username);
    }
}

