package com.example.vaccineManagementSystem.Exceptions;

public class PendingAppointment extends RuntimeException{
    public PendingAppointment(Integer id) {
        super("Your Appointment is pending with id : "+id);
    }
}
