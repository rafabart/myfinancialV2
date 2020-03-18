package com.myfinancial.model.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myfinancial.model.domain.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ExpenseRequest {

    @NotEmpty(message = "Campo obrigatório!")
    @Length(min = 3, max = 100, message = "O tamanho deve ser entre {min} e {max} caracteres!")
    private String description;

    @Positive(message = "O valor deve ser maior que zero!")
    private Double value;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt_BR")
    private LocalDate dueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt_BR")
    private LocalDate paymentDate;

    @NotEmpty(message = "Campo obrigatório!")
    private String expenseTypeString;

    @NotNull(message = "Campo obrigatório!")
    private Category category;
}
