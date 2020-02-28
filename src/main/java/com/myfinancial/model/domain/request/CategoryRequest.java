package com.myfinancial.model.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CategoryRequest {


    @NotEmpty(message = "Campo obrigatório!")
    @Length(min = 3, max = 40, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    private String name;
}
