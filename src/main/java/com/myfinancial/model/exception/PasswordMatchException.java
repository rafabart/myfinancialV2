package com.myfinancial.model.exception;

public class PasswordMatchException extends RuntimeException {

    public PasswordMatchException() {
        super("As senhas digitadas não correspondem");
    }

    public PasswordMatchException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
