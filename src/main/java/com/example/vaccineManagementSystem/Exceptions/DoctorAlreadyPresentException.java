package com.example.vaccineManagementSystem.Exceptions;

public class DoctorAlreadyPresentException extends RuntimeException{

    public DoctorAlreadyPresentException() {
        super("Doctor is already Present");
    }
}
