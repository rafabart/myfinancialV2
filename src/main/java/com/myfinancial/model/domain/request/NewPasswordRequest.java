package com.myfinancial.model.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class NewPasswordRequest {

    @NotEmpty(message = "Campo obrigatório!")
    @Length(min = 6, max = 60, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    private String password;

    @Length(min = 6, max = 60, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    @NotEmpty(message = "Campo obrigatório!")
    private String confirmPassword;
}
