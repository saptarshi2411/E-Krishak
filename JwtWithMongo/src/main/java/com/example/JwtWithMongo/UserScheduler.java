package com.example.JwtWithMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepo userRepo;


    @Scheduled(cron = "0 0 0/3 * * *")  
    public void sendScheduledEmail() {

        List<Users> allUsers = userRepo.findAll();

        for (Users user : allUsers) {
            String email = user.getEmail();

            if (email != null && !email.isEmpty()) {

                emailService.sendEmail(
                        email,
                        "From E-Krishak",
                        "Explore your wishlist."
                );
                System.out.println("Scheduled email sent successfully to " + email);
            } else {
                System.out.println("User " + user.getUsername() + " does not have a valid email.");
            }
        }
    }

}


