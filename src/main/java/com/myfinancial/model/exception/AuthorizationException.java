package com.myfinancial.model.exception;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
        super("Acesso negado!");
    }

    public AuthorizationException(final String message, final Throwable cause) {

        super(message, cause);
    }
}
