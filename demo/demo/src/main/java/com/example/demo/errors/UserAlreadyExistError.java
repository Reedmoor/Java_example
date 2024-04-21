package com.example.demo.errors;

public class UserAlreadyExistError extends Error{
    public UserAlreadyExistError(String message) {
        super(message);
    }
}
