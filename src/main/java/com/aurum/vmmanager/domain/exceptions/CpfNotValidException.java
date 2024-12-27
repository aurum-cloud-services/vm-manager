package com.aurum.vmmanager.domain.exceptions;

public class CpfNotValidException extends Exception {
    public CpfNotValidException() {
        super("Your cpf is not valid");
    }
}
