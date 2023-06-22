package com.example.vaccineManagementSystem.Exceptions;

public class OldEmailIdIsNotMatching extends RuntimeException {
    public OldEmailIdIsNotMatching() {
        super("Old EmailId is not matching");
    }
}
