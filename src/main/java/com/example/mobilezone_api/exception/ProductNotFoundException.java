package com.example.mobilezone_api.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String errMessage) {
        super(errMessage);
    }
}
