package com.myfinancial.model.domain.response;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Expense;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpenseResponse {

    private Long id;

    private String description;

    private Double value;

    private String expenseTypeString;

    private Category category;


    public ExpenseResponse(final Expense expense) {
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.expenseTypeString = expense.getExpenseType().getName();
        this.category = expense.getCategory();
    }
}
