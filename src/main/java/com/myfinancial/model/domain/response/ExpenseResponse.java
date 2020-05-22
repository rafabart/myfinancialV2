package com.myfinancial.model.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myfinancial.model.domain.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ExpenseResponse {

    private Long id;

    private String description;

    private Double value;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt_BR")
    private LocalDate dueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt_BR")
    private LocalDate paymentDate;

    private String expenseType;

    private Category category;
}
