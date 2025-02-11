package com.example.JwtWithMongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class Users {

    @Id
    private ObjectId id;
    private String email;
    private String username;
    private String password; // Assuming you have a password field
    private List<String> roles; // List of roles associated with the user
    private String address;
    private Long mobileno;

    private String otp;
    private Long otpRequestedTime;


    @DBRef
    private List<Product> products = new ArrayList<>(); // Initialize as an empty list

    @DBRef
    private List<WishList> wishlists = new ArrayList<>(); // Initialize as an empty list

    @DBRef
    private List<Blog> blogs = new ArrayList<>(); // Initialize as an empty list



    public Users() {
    }




    public Users(String username, String password, String address, Long mobileno, String email, List<String> roles, List<Product> products, List<WishList> wishlists,List<Blog> blogs) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.products = products;
        this.wishlists=wishlists;
        this.email=email;
        this.address = address;
        this.mobileno = mobileno;
        this.blogs = blogs;
    }


    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobileno() {
        return mobileno;
    }

    public void setMobileno(Long mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<WishList> getWishLists() {
        return wishlists;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishlists = wishLists;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
