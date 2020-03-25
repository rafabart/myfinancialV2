package com.myfinancial.model.exception;

public class CategoryExistingException extends RuntimeException {

    public CategoryExistingException() {
        super("Categoria já cadastrada!");
    }

    public CategoryExistingException(final Throwable cause) {

        super("Categoria já cadastrada!", cause);
    }
}