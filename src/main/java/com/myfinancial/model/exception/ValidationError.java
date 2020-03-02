package com.myfinancial.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ValidationError extends StandardError {


    private List<FieldMessageError> errors = new ArrayList<>();


    public ValidationError(final Long timeStamp, final Integer status, final String error, final String message, final String path) {
        super(timeStamp, status, error, message, path);
    }


    public void addError(final String fieldName, final String message) {
        errors.add(new FieldMessageError(fieldName, message));
    }
}