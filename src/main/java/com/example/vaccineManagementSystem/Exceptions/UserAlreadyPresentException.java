package com.example.vaccineManagementSystem.Exceptions;

public class UserAlreadyPresentException extends RuntimeException{

    public UserAlreadyPresentException() {
        super("user is already present");
    }
}
