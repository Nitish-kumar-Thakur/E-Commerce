package com.ecommerce.service;

import com.ecommerce.dto.UserDto;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.entities.UserStatus;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CartRepo;
import com.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private EmailService emailService;
    @Autowired
    UserRepo repo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CartService cartService;

    public UserDto save(User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set user status based on role
        if (user.getRole().equals(Role.VENDOR)) {
            user.setStatus(UserStatus.PENDING);
        } else if (user.getRole().equals(Role.CUSTOMER)) {
            user.setStatus(UserStatus.APPROVED);
        }

        // Save the user to the database
        User savedUser = repo.save(user);



        if(savedUser.getRole().equals(Role.CUSTOMER)){
            // Create and associate a cart with the saved user
            Cart cart = new Cart();
            cart.setUser(savedUser); // Associate the cart with the user
            cartRepo.save(cart); // Save the cart
            emailService.sendCustomerApprovedEmail(savedUser.getEmail(), savedUser.getName());
        }
        else if (savedUser.getRole().equals(Role.VENDOR)) {
//            messagingTemplate.convertAndSend("/admin/notifications", "New vendor registered: " + user.getEmail());
            emailService.sendVendorRegisteredEmail(savedUser.getEmail(), savedUser.getName());
        }


        // Return the user details as a DTO
        return userToDto(savedUser);
    }

    public List<UserDto> roleUsers(Role role){
        List<User> users= repo.findByRole(role);
        return users.stream().map(this::userToDto).toList();
    }
    public List<UserDto> statusUsers(UserStatus status){
        List<User> users= repo.findByStatus(status);
        return users.stream().map(this::userToDto).toList();
    }
    public List<UserDto> users(){
        List<User> users = repo.findAll();

        return users.stream().filter(user -> user.getRole()!=Role.ADMIN).map(this::userToDto).toList();
    }
    public void updateUserStatus(Long id){
        User user = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vendor"));
        if (user.getStatus().equals(UserStatus.APPROVED)){
            repo.save(user);
        } else if (user.getStatus().equals(UserStatus.REJECTED)) {
            user.setStatus(UserStatus.REJECTED);
            repo.save(user);
        }
    }

    public UserDto update(Long id){
        User user = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user"));
        user.setPassword(passwordEncoder.encode("password@123"));
        User user1 = repo.save(user);
        return userToDto(user1);
    }
    /// //////////////////////////////////////conversion////////////////////

    public UserDto userToDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getGender(), user.getRole(),user.getStatus());
    }
}
