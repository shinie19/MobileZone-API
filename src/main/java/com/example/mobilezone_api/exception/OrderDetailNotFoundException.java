package com.example.mobilezone_api.exception;

public class OrderDetailNotFoundException extends RuntimeException{
    public OrderDetailNotFoundException(String message) {
        super(message);
    }
}
