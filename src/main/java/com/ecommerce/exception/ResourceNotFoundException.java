package com.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException{
     String error;

    public ResourceNotFoundException(String error){
        super(String.format("%s not found",error));
        this.error=error;
    }

}
