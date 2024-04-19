package com.example.demo.exception;

public class UserAlreadyExistError extends Exception{
    public UserAlreadyExistError(String message) {
        super(message);
    }
}
