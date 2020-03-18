package com.myfinancial.model.exception;

public class CategoryExistingException extends RuntimeException {

    public CategoryExistingException() {
        super("Categoria já cadastrado!");
    }

    public CategoryExistingException(final Throwable cause) {

        super("Categoria já cadastrado!", cause);
    }
}