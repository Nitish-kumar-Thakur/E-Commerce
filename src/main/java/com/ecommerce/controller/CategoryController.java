package com.ecommerce.controller;

import com.ecommerce.dto.ResponseCategory;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entities.Category;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Category category){
        ResponseCategory category1 = service.save(category);
        Map<String,Object> response = Map.of("Status",true,
                "message", "success", "data",category1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> categories(){
        List<ResponseCategory> category1 = service.categories();
        Map<String,Object> response = Map.of("Status",true,
                "message", "success", "data",category1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/")
//    public ResponseEntity<?> category(@RequestBody Long id ){
//        Category category1 = service.category(id);
//        Map<String,Object> response = Map.of("Status",true,
//                "message", "success", "data",category1);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
