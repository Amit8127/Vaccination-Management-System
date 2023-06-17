package com.example.vaccineManagementSystem.Exceptions;

public class VaccinationAddressCanNotNull extends RuntimeException{
    public VaccinationAddressCanNotNull() {
        super("Vaccination Centre address can not null");
    }
}
