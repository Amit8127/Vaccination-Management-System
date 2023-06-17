package com.example.vaccineManagementSystem.Exceptions;

public class DoctorNotFound extends RuntimeException{
    public DoctorNotFound() {
        super("Doctor dose not Exists");
    }
}
