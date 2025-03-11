package com.ecommerce.service;

import com.ecommerce.dto.InsertProduct;
import com.ecommerce.dto.ResponseCategory;
import com.ecommerce.dto.ResponseProduct;
import com.ecommerce.entities.*;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.exception.VendorErrorException;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo pRepo;
    @Autowired
    UserRepo uRepo;
    @Autowired
    CategoryRepo cRepo;

    public ResponseProduct save(InsertProduct product){
        List<Category> categories = product.getCategories().stream().map(id-> cRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category"))).toList();
        User user = uRepo.findById(product.getUser()).orElseThrow(()-> new ResourceNotFoundException("User"));
        if (user.getStatus().equals(UserStatus.PENDING)){
            throw new VendorErrorException("Your Account Status is under pending");
        } else if (user.getStatus().equals(UserStatus.REJECTED)) {
            throw new VendorErrorException("Your Account Status is Rejected");
        }
        Product pro = new Product();
        pro.setName(product.getName());
        pro.setPrice(product.getPrice());
        pro.setImageUrl(product.getImageUrl());
        pro.setDescription(product.getDescription());
        pro.setUser(user);
        pro.setCategories(categories);
        for (Category category : categories) {
            category.getProducts().add(pro);
        }
        Product savedProduct = pRepo.save(pro);
        cRepo.saveAll(categories);
        return productToDto(savedProduct);
    }

    public List<ResponseProduct> product(){
//        System.out.println("@@@@@@@@@@@@@@@@@@@");
//            System.out.println(pRepo.findAll().stream().map(e->e.getCategories()).toList());
        return pRepo.findAll().stream().map(this::productToDto).toList();
    }

    public List<ResponseProduct> products(Long id){
        return pRepo.findAllByCategoriesId(id).stream().map(this::productToDto).toList();
    }
    public List<ResponseProduct> productsByUSer(Long id){
        return pRepo.findAllByUserId(id).stream().map(this::productToDto).toList();
    }
    public List<ResponseProduct> productsByCategories(List<Long> ids){
        return pRepo.findAllByCategories(ids).stream().map(this::productToDto).toList();
    }

    /// //////////////////////conversion////////////////////////////////
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
