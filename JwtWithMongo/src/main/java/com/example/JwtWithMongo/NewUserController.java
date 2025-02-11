package com.example.JwtWithMongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class NewUserController {

    @Autowired
    NewUserService newUserService;

    @Autowired
    UserService userService;

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable ObjectId id, @RequestBody Users user) {
        Optional<Users> userInDb = newUserService.findById(id);

        if (userInDb.isPresent()) {
            Users existingUser = userInDb.get();
            existingUser.setUsername(user.getUsername()); // Assuming the user entity has a setUsername method
            existingUser.setPassword(user.getPassword()); // Make sure password is encrypted
            userService.register(existingUser); // Save the updated user
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId id) {
        Optional<Users> userInDb = newUserService.findById(id);

        if (userInDb.isPresent()) {
            newUserService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

