package com.ecommerce.controller;

import com.ecommerce.dto.ResponseProduct;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.UserStatus;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
    @Autowired
    ProductService pService;
    @Autowired
    UserService service;
    @GetMapping("/")
    public ResponseEntity<?> users(){
        List<UserDto> users = service.users();
        Map<String,Object> response = Map.of("Status",true,
                "message", "success", "data",users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        try {
            Role role1 = Role.valueOf(role.toUpperCase()); // Convert string to ENUM
            List<UserDto> users = service.roleUsers(role1);
            Map<String, Object> response = Map.of(
                    "status", true,
                    "message", "success",
                    "data", users
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException | NullPointerException e) {
            return new ResponseEntity<>(Map.of(
                    "status", false,
                    "message", "Invalid role provided"
            ), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/product")
    public  ResponseEntity<?> products(){
        List<ResponseProduct> products = pService.product();
        Map<String,Object> response = Map.of("Status",true,
                "message", "success", "data",products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getUsersByStatus(@PathVariable String status) {
        try {
            UserStatus status1 = UserStatus.valueOf(status.toUpperCase()); // Convert string to ENUM
            List<UserDto> users = service.statusUsers(status1);
            Map<String, Object> response = Map.of(
                    "status", true,
                    "message", "success",
                    "data", users
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException | NullPointerException e) {
            return new ResponseEntity<>(Map.of(
                    "status", false,
                    "message", "Invalid status provided"
            ), HttpStatus.BAD_REQUEST);
        }
    }

}
