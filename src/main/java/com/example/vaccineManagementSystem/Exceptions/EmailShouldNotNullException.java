package com.example.vaccineManagementSystem.Exceptions;

public class EmailShouldNotNullException extends RuntimeException{
    public EmailShouldNotNullException() {
        super("Email should not be NULL");
    }
}
