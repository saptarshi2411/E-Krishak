package com.example.JwtWithMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
public class MandiController {

    @Autowired
    MandiRepo mandiRepo;

    @Autowired
    MandiService mandiService;
    @GetMapping("/allMandiPrice")
    public ResponseEntity<List<MandiPrice>> getAllMandiPrices() {
        List<MandiPrice> allMandiPrices = mandiService.getAll();
        if (allMandiPrices != null && !allMandiPrices.isEmpty()) {
            return new ResponseEntity<>(allMandiPrices, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/categoryProductsOfMandi/{category}")
    public ResponseEntity<List<MandiPrice>> categoryProducts(@PathVariable String category) {
        List<MandiPrice> categoryProducts = mandiService.getCategoryProducts(category);
        if (categoryProducts != null && !categoryProducts.isEmpty()) {
            return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/placesOfMandi/{place}")
    public ResponseEntity<List<MandiPrice>> categoryPlace(@PathVariable String place) {
        List<MandiPrice> categoryProducts = mandiService.getPlace(place);
        if (categoryProducts != null && !categoryProducts.isEmpty()) {
            return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("admin/addMandiProduct")
    public ResponseEntity<?> addMandiPrice(@RequestBody MandiPrice mandiPrice) {
        MandiPrice savedMandiPrice = mandiService.addMandiPrice(mandiPrice);
        return new ResponseEntity<>(savedMandiPrice, HttpStatus.CREATED);
    }


}
