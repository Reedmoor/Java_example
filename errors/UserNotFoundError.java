package com.example.demo.errors;

public class UserNotFoundError extends Error {
    public UserNotFoundError(String message) {
        super(message);
    }
}
