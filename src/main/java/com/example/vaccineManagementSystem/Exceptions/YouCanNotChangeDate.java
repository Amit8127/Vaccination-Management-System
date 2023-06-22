package com.example.vaccineManagementSystem.Exceptions;

public class YouCanNotChangeDate extends RuntimeException{
    public YouCanNotChangeDate() {
        super("You can not change date your appointment is completed");
    }
}
