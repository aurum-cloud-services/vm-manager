package com.aurum.vmmanager.domain.exceptions.domain;

public class PasswordNotValidException extends Exception {
    public PasswordNotValidException() {
        super("Your password needs to be between 12 and 100 chars ");
    }
}
