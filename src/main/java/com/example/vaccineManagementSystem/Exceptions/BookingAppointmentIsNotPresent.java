package com.example.vaccineManagementSystem.Exceptions;

public class BookingAppointmentIsNotPresent extends RuntimeException{
    public BookingAppointmentIsNotPresent() {
        super("Booking dose not exists");
    }
}
