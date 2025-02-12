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
        
        Product product = productRepository.findByProdId(productId).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        
        Users user = userRepository.findByUsername(username);
        if (user != null) {
            
            WishList wishList;
            if (user.getWishLists() != null && !user.getWishLists().isEmpty()) {
                wishList = user.getWishLists().get(0); 
            } else {
                wishList = new WishList();
                wishList.setProducts(new ArrayList<>());
                int randomId = new Random().nextInt(1_000_000); 
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
        Users user = userRepository.findByUsername(username);
        List<WishList> userWishlist = user.getWishLists();
        if (userWishlist == null || userWishlist.isEmpty()) {
            return new ArrayList<>(); 
        }
        Set<Product> userWishListProducts = userWishlist.stream()
                .flatMap(wishList -> wishList.getProducts().stream()) 
                .collect(Collectors.toSet()); 
        return new ArrayList<>(userWishListProducts);
    }

    public void deleteProductFromWishList(ObjectId wishlistId, String productId) {
        WishList wishList = wishListRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        List<Product> products = wishList.getProducts();
        int prodId = Integer.parseInt(productId);

        Product productToRemove = products.stream()
                .filter(product -> product.getProdId() == prodId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in wishlist"));

        products.remove(productToRemove);
        wishListRepository.save(wishList);
    }

}
