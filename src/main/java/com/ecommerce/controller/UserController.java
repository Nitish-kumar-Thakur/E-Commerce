package com.ecommerce.controller;

import com.ecommerce.dto.UserDto;
import com.ecommerce.entities.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/")
    public ResponseEntity<?> save( @RequestBody User user){
        UserDto data= service.save(user);

        Map<String,Object> response = Map.of("Status", true, "message","success","data", data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @PathVariable Long id){
        UserDto data= service.update(id);

        Map<String,Object> response = Map.of("Status", true, "message","success","data", data);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
