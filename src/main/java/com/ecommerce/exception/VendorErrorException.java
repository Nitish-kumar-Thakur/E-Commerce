package com.ecommerce.exception;

public class VendorErrorException extends RuntimeException{
    String error;
    public VendorErrorException(String error){
        super(String.format("%s",error));
        this.error = error;
    }
}
