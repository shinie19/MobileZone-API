package com.example.mobilezone_api.exception;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
