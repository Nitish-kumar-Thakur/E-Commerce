package com.ecommerce.exception;

import jakarta.servlet.ServletException;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public class JwtErrorException extends IOException {
    JwtErrorException(){
        super();
    }
}
