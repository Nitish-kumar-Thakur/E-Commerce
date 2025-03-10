package com.ecommerce.service;

import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.LoginResponse;
import com.ecommerce.jwt.JwtHelper;
import com.ecommerce.securitycomponent.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private CustomUserDetail userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    public LoginResponse login(LoginRequest request){
      manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Get the role as string
                .findFirst() // Assuming a user has only one role, get the first one
                .orElse("USER");
        Long userId = null;
        if (userDetails instanceof com.ecommerce.entities.User) {
            userId = ((com.ecommerce.entities.User) userDetails).getId(); // Get userId
        }
        return new LoginResponse(userId,role,token);
    }
}
