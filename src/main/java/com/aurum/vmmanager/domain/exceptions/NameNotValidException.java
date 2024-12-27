package com.aurum.vmmanager.domain.exceptions;

public class NameNotValidException extends Exception {
    public NameNotValidException() {
        super("Name need to be lower than 40");
    }
}
