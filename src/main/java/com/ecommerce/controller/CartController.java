package com.ecommerce.controller;

import com.ecommerce.dto.InsertCartProduct;
import com.ecommerce.dto.ResponseCart;
import com.ecommerce.service.CartService;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody InsertCartProduct cartProduct) {
        ResponseCart responseCart = cartService.addProductToCart(cartProduct);
        // Create a response map
        Map<String, Object> response = Map.of(
                "Status", true,
                "message", "Product added to cart successfully",
                "data", responseCart
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/removeFromCart")
    public ResponseEntity<?> removeFromCart(@RequestBody InsertCartProduct cartProduct) {
        ResponseCart responseCart = cartService.removeProductFromCart(cartProduct);
        Map<String, Object> response = Map.of(
                "Status", true,
                "message", "Product removed from cart successfully",
                "data", responseCart
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
