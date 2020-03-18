package com.myfinancial.model.exception;

public class EmailSenderException extends RuntimeException {

    public EmailSenderException(final String message) {
        super(message);
    }

    public EmailSenderException(final String message, final Throwable cause) {

        super(message, cause);
    }
}
