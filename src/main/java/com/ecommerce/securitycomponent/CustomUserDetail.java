package com.ecommerce.securitycomponent;

import com.ecommerce.entities.User;
import com.ecommerce.exception.CredentialErrorException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetail implements UserDetailsService {
    @Autowired
    UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email)
                .orElseThrow(()-> new CredentialErrorException(" Invalid Username or Password  !!"));
    }
}
