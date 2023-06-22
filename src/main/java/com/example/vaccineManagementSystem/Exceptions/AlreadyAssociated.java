package com.example.vaccineManagementSystem.Exceptions;

public class AlreadyAssociated extends RuntimeException{
    public AlreadyAssociated(String centreName) {
        super("Doctor is Already associated to: " + centreName);
    }
}
