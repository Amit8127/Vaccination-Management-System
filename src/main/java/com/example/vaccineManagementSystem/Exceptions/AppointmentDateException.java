package com.example.vaccineManagementSystem.Exceptions;

public class AppointmentDateException extends RuntimeException{
    public AppointmentDateException(String dateTime) {
        super("Your Appointment Date and Time is: "+dateTime);
    }
}
