package com.example.mobilezone_api.exception;

public class ColorNotFoundException extends RuntimeException{
    public ColorNotFoundException(String errMessage) {
        super(errMessage);
    }
}
