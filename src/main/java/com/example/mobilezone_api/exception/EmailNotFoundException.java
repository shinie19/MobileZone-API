package com.example.mobilezone_api.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
