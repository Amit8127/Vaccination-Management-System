package com.example.vaccineManagementSystem.Exceptions;

public class DoctorDoesNotExistsByThisEmail extends RuntimeException{
    public DoctorDoesNotExistsByThisEmail() {
        super("Doctor does not exists with this EmailId");
    }
}
