package com.ecommerce.controller;

import com.ecommerce.dto.InsertProduct;
import com.ecommerce.dto.RequestCategories;
import com.ecommerce.dto.RequestUserId;
import com.ecommerce.dto.ResponseProduct;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody InsertProduct product){
        ResponseProduct product1 = service.save(product);
        Map<String,Object> response = Map.of("Status",true,
                "message", "success", "data",product1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> product(@PathVariable Long id){
        List<ResponseProduct> products = service.products(id);
        Map<String,Object> response = Map.of("Status",true,
                "message", "success", "data",products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<?> productsByUser(@RequestParam("user_id") Long userId) {
        List<ResponseProduct> products = service.products(userId);
        Map<String, Object> response = Map.of(
                "Status", true,
                "message", "success",
                "data", products
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<?> productsByCategories(@RequestBody RequestCategories categories) {
        List<ResponseProduct> products = service.productsByCategories(categories.getCategory_ids());
        Map<String, Object> response = Map.of(
                "Status", true,
                "message", "success",
                "data", products
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
