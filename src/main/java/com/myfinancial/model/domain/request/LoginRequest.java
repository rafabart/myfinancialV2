package com.myfinancial.model.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "Email inválido!")
    @NotEmpty(message = "Campo obrigatório!")
    private String email;

    @NotEmpty(message = "Campo obrigatório!")
    private String password;
}
