package com.aurum.vmmanager.domain.exceptions.domain;

public class CpfNotValidException extends Exception {
    public CpfNotValidException() {
        super("Your cpf is not valid");
    }
}
