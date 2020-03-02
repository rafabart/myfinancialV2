package com.myfinancial.model.exception;

public class EmailExistingException extends RuntimeException {

    public EmailExistingException() {
        super("Email já cadastrado!");
    }

    public EmailExistingException(final Throwable cause) {

        super("Email já cadastrado!", cause);
    }
}