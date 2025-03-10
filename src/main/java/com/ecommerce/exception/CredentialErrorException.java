package com.ecommerce.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class CredentialErrorException extends RuntimeException {
    String error;
    public CredentialErrorException(String error){
        super(String.format("%s",error));
        this.error=error;
    }
}
