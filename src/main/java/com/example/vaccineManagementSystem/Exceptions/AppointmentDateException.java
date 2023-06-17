package com.example.vaccineManagementSystem.Exceptions;

public class AppointmentDateException extends RuntimeException{
    public AppointmentDateException(String date) {
        super("Your Appointment date is: "+date);
    }
}
