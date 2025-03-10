package com.ecommerce.service;

import com.ecommerce.dto.ResponseCategory;
import com.ecommerce.entities.Category;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo repo;
    public ResponseCategory save(Category category){
        Category cat= repo.save(category);
        return categoryToDto(cat);
    }

    public List<ResponseCategory> categories(){
        List<Category> cat = repo.findAll();
        return cat.stream().map(this::categoryToDto).toList();
    }

    public ResponseCategory category(Long id){
        Category cat = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category"));
        return categoryToDto(cat);
    }

    /// //////////////////////////////////////////////

    public ResponseCategory categoryToDto(Category cat){
        return new ResponseCategory(cat.getId(),cat.getName());
    }
}
