package com.example.vaccineManagementSystem.Dtos.RequestDtos;

public class AlreadyAssociatedDtos extends RuntimeException{
    public AlreadyAssociatedDtos(String centreName) {
        super("Doctor is Already associated to: " + centreName);
    }
}
