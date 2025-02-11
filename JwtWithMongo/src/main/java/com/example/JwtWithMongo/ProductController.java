package com.example.JwtWithMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = service.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/otherProducts")
    public ResponseEntity<List<Product>> getOtherProducts() {
        List<Product> otherProducts = service.getOtherUsersProductsHome();
        return new ResponseEntity<>(otherProducts, HttpStatus.OK);
    }


    @GetMapping("/ownProducts")
    public ResponseEntity<List<Product>> getOwnProducts() {
        List<Product> ownProducts = service.getUserOwnProducts();
        return new ResponseEntity<>(ownProducts, HttpStatus.OK);
    }


    @GetMapping("/categoryProducts/{category}")
    public ResponseEntity<List<Product>> getCategoryProducts(@PathVariable String category) {
        List<Product> categoryProducts = service.getCategoryProducts(category);
        return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
    }


    @GetMapping("/products/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId) {
        Product product = service.getProductById(prodId);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }


    @PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(
            @RequestParam("proName") String proName,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("price") String quantity,
            @RequestParam("category") String category,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Product product = new Product();
            product.setProName(proName);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setQuantity(quantity);

            if (image != null && !image.isEmpty()) {
                product.setImageData(image.getBytes());
            }


            int prodId = (int) (Math.random() * 1000000); // Random number as ID
            product.setProdId(prodId);


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();


            service.addProduct(product, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/products/{prodId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(
            @PathVariable int prodId,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Product product = service.getProductById(prodId);

            if (product == null) {
                return ResponseEntity.notFound().build();
            }

            product.setProName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);


            if (image != null && !image.isEmpty()) {
                product.setImageData(image.getBytes());
            }

            service.updateProduct(product, prodId);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/products/{prodId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int prodId) {
        try {
            service.deleteProduct(prodId);
            return ResponseEntity.noContent().build(); // Successfully deleted
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
