package com.example.JwtWithMongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class WishListController {

    @Autowired
    WishListService service;

    @GetMapping("/wishlist")
    public List<Product> getProducts() {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return service.getWishListProducts(username);
    }

    @PostMapping("/wishlist/{productId}")
    public void addwishlistProducts(@PathVariable int productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        service.addProductToWishList(productId,username);
    }

    @DeleteMapping("/{wishlistId}/wishlist/{productId}")
    public ResponseEntity<Void> deletewishlistProducts(@PathVariable ObjectId wishlistId, @PathVariable String productId) {
        service.deleteProductFromWishList(wishlistId, productId); // No change needed here
        return ResponseEntity.noContent().build();
    }


}












