package com.myfinancial.model.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Expense;
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

    private String expenseTypeString;

    private Category category;


    public ExpenseResponse(final Expense expense) {
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.dueDate = expense.getDueDate();
        this.paymentDate = expense.getPaymentDate();
        this.expenseTypeString = expense.getExpenseType().getName();
        this.category = expense.getCategory();
    }
}
