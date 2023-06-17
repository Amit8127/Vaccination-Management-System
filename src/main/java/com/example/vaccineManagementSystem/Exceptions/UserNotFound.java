package com.example.vaccineManagementSystem.Exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("User dose not Exists");
    }
}
