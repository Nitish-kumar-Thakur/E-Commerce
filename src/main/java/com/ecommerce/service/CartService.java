package com.ecommerce.service;

import com.ecommerce.dto.InsertCartProduct;
import com.ecommerce.dto.ResponseCart;
import com.ecommerce.dto.ResponseCategory;
import com.ecommerce.dto.ResponseProduct;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CartRepo;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepo cRepo;
    @Autowired
    private UserRepo uRepo;
    @Autowired
    private ProductRepo pRepo;

    public ResponseCart save(Cart cart){
        Cart cart1 = cRepo.save(cart);
        return cartToDto(cart1);
    }
    public ResponseCart addProductToCart(InsertCartProduct cartProduct) {
        Product product = pRepo.findById(cartProduct.getProduct_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product"));
        // Find the user's cart
        User user = uRepo.findById(cartProduct.getUser_id()).orElseThrow(() -> new ResourceNotFoundException("Cart"));
        Cart cart = cRepo.findByUser(user).orElseGet(() -> createNewCart(user));  // Create a new cart if the user doesn't have one.

        // Add the product to the cart
        cart.getProducts().add(product);
        cRepo.save(cart);

        // Return the updated cart
        return cartToDto(cart);
    }

    public ResponseCart removeProductFromCart(InsertCartProduct cartProduct) {
        Product product = pRepo.findById(cartProduct.getProduct_id())
                .orElseThrow(() -> new ResourceNotFoundException("Product"));
        User user = uRepo.findById(cartProduct.getUser_id()).orElseThrow(() -> new ResourceNotFoundException("User"));
        // Find the user's cart
        Cart cart = cRepo.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart"));

        // Remove the product from the cart
        cart.getProducts().remove(product);
        cRepo.save(cart);

        // Return the updated cart
        return cartToDto(cart);
    }

    private Cart createNewCart(User user) {
        // If no cart exists for the user, create one
        Cart cart = new Cart();
        cart.setUser(user);
        return cRepo.save(cart);
    }
    /// /////////////////////////////////////////////////////////
    public ResponseCart cartToDto(Cart cart){
        return new ResponseCart(cart.getId(),cart.getUser().getId(),cart.getUser().getName(),cart.getProducts().stream().map(this::productToDto).toList());
    }
    public ResponseProduct productToDto(Product product){
        ResponseProduct ResponseProduct = new ResponseProduct();
        ResponseProduct.setId(product.getId());
        ResponseProduct.setName(product.getName());
        ResponseProduct.setPrice(product.getPrice());
        ResponseProduct.setImageUrl(product.getImageUrl());
        ResponseProduct.setVendor_id(product.getUser().getId());
        ResponseProduct.setVendor_name(product.getUser().getName());
        ResponseProduct.setDescription(product.getDescription());
        ResponseProduct.setCategories(product.getCategories().stream().map(this::categoryToDto).toList());
        return ResponseProduct;
    }
    public ResponseCategory categoryToDto(Category cat){
        return new ResponseCategory(cat.getId(),cat.getName());
    }

}
