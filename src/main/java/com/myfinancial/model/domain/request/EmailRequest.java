package com.myfinancial.model.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class EmailRequest {

    @Email(message = "Email inválido!")
    @NotEmpty(message = "O campo email esta vazio!")
    private String email;
}
