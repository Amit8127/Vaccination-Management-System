package com.example.vaccineManagementSystem.Exceptions;

public class UserAlreadyVaccinated extends RuntimeException{
    public UserAlreadyVaccinated() {
        super("User already vaccinated");
    }
}
