package com.example.JwtWithMongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

    @Id
    private ObjectId id;
    private int prodId;
    private String proName;
    private double price;
    private String category;
    private String quantity;
    private String description;


    private byte[] imageData;  // to store the image data
    private String imageName;   // to store the image name
    private String imageType;   // to store the image type (e.g., "image/jpeg")


    public Product() {
    }


    public Product(ObjectId id, int prodId, String proName, double price, String category,
                   String description, String quantity, byte[] imageData, String imageName, String imageType) {
        this.prodId = prodId;
        this.proName = proName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.imageData = imageData;
        this.imageName = imageName;
        this.imageType = imageType;
        this.id = id;
    }



    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
