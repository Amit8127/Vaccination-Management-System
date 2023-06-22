package com.example.vaccineManagementSystem.Exceptions;

public class EmailIsAlreadyPresent extends RuntimeException{

    public EmailIsAlreadyPresent() {
        super("This EmailId is already present");
    }
}
