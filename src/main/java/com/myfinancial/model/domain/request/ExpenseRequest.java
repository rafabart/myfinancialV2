package com.myfinancial.model.domain.request;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.enums.ExpenseType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class ExpenseRequest {

    @NotEmpty(message = "Campo obrigatório!")
    @Length(min = 3, max = 100, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    private String description;

    @Positive(message = "O valor deve ser maior que zero!")
    private Double value;

    @NotEmpty(message = "Campo obrigatório!")
    private String expenseTypeString;

    @NotEmpty(message = "Campo obrigatório!")
    private Category category;
}
