package com.example.vaccineManagementSystem.Exceptions;

public class YouHavePendingAppointmentOnCentre extends RuntimeException{
    public YouHavePendingAppointmentOnCentre() {
        super("You have pending appointment on current vaccination centre");
    }
}
