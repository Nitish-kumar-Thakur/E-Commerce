package com.ecommerce.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex) {
        String error = ex.getMessage();
        Map<String, Object> response = Map.of("status", false, "message", error);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // CredentialErrorException
    @ExceptionHandler(CredentialErrorException.class)
    public ResponseEntity<?> credentialErrorException(CredentialErrorException ex) {
        String error = ex.getMessage();
        Map<String, Object> response = Map.of("status", false, "message", error);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // AuthenticationException
    @ExceptionHandler(javax.naming.AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(javax.naming.AuthenticationException ex) {
        String error = "Access Denied !! " + ex.getMessage();
        Map<String, Object> response = Map.of("status", false, "message", error);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // BadCredentialsException
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> exceptionHandler() {
        String error = "Credentials Invalid !!";
        Map<String, Object> response = Map.of("status", false, "message", error);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Handle Expired JWT Token
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex) {
        System.out.println("Expired JWT Token Exception: " + ex.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("error", "JWT Token Expired");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    // Handle Other Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException ex){
        String error = ex.getMessage();
        Map<String, Object> response = Map.of("status", false, "message", error);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
