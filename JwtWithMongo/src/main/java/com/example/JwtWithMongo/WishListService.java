package com.example.JwtWithMongo;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WishListService {

    @Autowired
    private WishListRepo wishListRepository;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private UserRepo userRepository;

    public void addProductToWishList(int productId, String username) {
        // Fetch the product by productId
        Product product = productRepository.findByProdId(productId).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        // Fetch the user
        Users user = userRepository.findByUsername(username);
        if (user != null) {
            // Fetch or create the user's wishlist
            WishList wishList;
            if (user.getWishLists() != null && !user.getWishLists().isEmpty()) {
                wishList = user.getWishLists().get(0); // Assuming the user has only one wishlist
            } else {
                // Create a new wishlist and assign a random ID
                wishList = new WishList();
                wishList.setProducts(new ArrayList<>());


                int randomId = new Random().nextInt(1_000_000); // Example range: 0 to 999999
                wishList.setRandomId(randomId);


                user.setWishLists(Collections.singletonList(wishList));
            }


            if (!wishList.getProducts().contains(product)) {
                wishList.getProducts().add(product);
            }


            wishListRepository.save(wishList);
            userRepository.save(user);
        }
    }


    public List<Product> getWishListProducts(String username) {
        // Fetch the user by username
        Users user = userRepository.findByUsername(username);

        // Get the user's wishlist
        List<WishList> userWishlist = user.getWishLists();

        // Check if the user has any wish lists
        if (userWishlist == null || userWishlist.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if no wishlists
        }

        // Collect all products from the user's wishlist (assumes WishList has a list of Product objects)
        Set<Product> userWishListProducts = userWishlist.stream()
                .flatMap(wishList -> wishList.getProducts().stream()) // Get all products from each wishlist
                .collect(Collectors.toSet()); // Collect to a set to avoid duplicates

        // Return the list of products
        return new ArrayList<>(userWishListProducts);
    }

    public void deleteProductFromWishList(ObjectId wishlistId, String productId) {
        WishList wishList = wishListRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        List<Product> products = wishList.getProducts();

        // Convert the String productId to int
        int prodId = Integer.parseInt(productId);

        Product productToRemove = products.stream()
                .filter(product -> product.getProdId() == prodId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in wishlist"));

        products.remove(productToRemove);
        wishListRepository.save(wishList);
    }

}
