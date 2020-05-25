package com.myfinancial.model.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CustomerRequest {

    private Long id;

    @NotEmpty(message = "Campo obrigatório!")
    @Length(min = 3, max = 80, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    private String name;

    @Email(message = "Email inválido!")
    @NotEmpty(message = "Campo obrigatório!")
    private String email;

    @NotEmpty(message = "Campo obrigatório!")
    private String profileType;
}
