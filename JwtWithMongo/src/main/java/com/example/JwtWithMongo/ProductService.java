package com.example.JwtWithMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    @Autowired
    UserRepo userRepo;

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(int prodId) {
        return repo.findByProdId(prodId).orElse(new Product());
    }

    public void addProduct(Product prod, String username) {
        Product savedProduct = repo.save(prod);


        Users user = userRepo.findByUsername(username);
        if (user != null) {
            user.getProducts().add(savedProduct); // Assuming getProducts() returns a List<Product>
            userRepo.save(user); // Save the updated user
        }
    }

    public void updateProduct(Product prod, int prodId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        Product existingProduct = repo.findByProdId(prodId).orElseThrow(() -> new RuntimeException("Product not found"));


        Users user = userRepo.findByUsername(username);
        if (user != null && user.getProducts().stream().anyMatch(p -> p.getProdId() == prodId)) {

            existingProduct.setProName(prod.getProName());
            existingProduct.setPrice(prod.getPrice());



            repo.save(existingProduct);
        } else {
            throw new RuntimeException("You are not authorized to update this product");
        }
    }

    public void deleteProduct(int prodId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        Product product = repo.findByProdId(prodId).orElseThrow(() -> new RuntimeException("Product not found"));


        Users user = userRepo.findByUsername(username);
        if (user != null && user.getProducts().stream().anyMatch(p -> p.getProdId() == prodId)) {

            List<Users> usersWithProduct = userRepo.findByProductsContains(product);  // Custom query method

            for (Users userWithProduct : usersWithProduct) {
                userWithProduct.getProducts().removeIf(p -> p.getProdId() == product.getProdId());
                userRepo.save(userWithProduct); // Save the updated user to MongoDB
            }


            repo.deleteByProdId(prodId);
        } else {
            throw new RuntimeException("You are not authorized to delete this product");
        }
    }


    public List<Product> getOtherUsersProductsHome() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        Users user = userRepo.findByUsername(username);
        List<Product> userProducts = user.getProducts();


        Set<Integer> userProductIds = userProducts.stream()
                .map(Product::getProdId) // Assuming `getId()` returns a unique identifier for the product
                .collect(Collectors.toSet());


        List<Product> allProducts = repo.findAll();
        return allProducts.stream()
                .filter(product -> !userProductIds.contains(product.getProdId())) // Filter based on product IDs
                .collect(Collectors.toList());
    }

    public List<Product> getUserOwnProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = userRepo.findByUsername(username);


        return user.getProducts();
    }

    public List<Product> getCategoryProducts(String category) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        Users user = userRepo.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }


        Set<Integer> userProductIds = user.getProducts().stream()
                .map(Product::getProdId)
                .collect(Collectors.toSet());


        List<Product> categoryProducts = repo.findByCategory(category);


        return categoryProducts.stream()
                .filter(product -> !userProductIds.contains(product.getProdId())) // Exclude user's products
                .collect(Collectors.toList());
    }

}


