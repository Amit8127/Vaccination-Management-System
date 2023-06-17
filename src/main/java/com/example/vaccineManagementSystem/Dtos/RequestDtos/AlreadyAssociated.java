package com.example.vaccineManagementSystem.Dtos.RequestDtos;

public class AlreadyAssociated extends RuntimeException{
    public AlreadyAssociated(String centreName) {
        super("Doctor is Already associated to: " + centreName);
    }
}
