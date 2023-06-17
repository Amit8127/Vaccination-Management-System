package com.example.vaccineManagementSystem.Exceptions;

public class UserIsNotVaccinated extends RuntimeException{
    public UserIsNotVaccinated() {
        super("User is not vaccinated");
    }
}
