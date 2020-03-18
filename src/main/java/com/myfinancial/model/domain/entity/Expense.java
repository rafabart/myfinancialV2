package com.myfinancial.model.domain.entity;

import com.myfinancial.model.domain.enums.ExpenseType;
import com.myfinancial.model.domain.request.ExpenseRequest;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Expense extends IdAbstract {

    @NotNull
    @Length(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String description;


    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    private Double value;


    private LocalDate dueDate;

    private LocalDate paymentDate;


    @NotNull
    @Column(nullable = false)
    private int expenseType;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Expense(final ExpenseRequest expenseRequest) {
        this.description = expenseRequest.getDescription();
        this.value = expenseRequest.getValue();
        this.dueDate = expenseRequest.getDueDate();
        this.paymentDate = expenseRequest.getPaymentDate();
        this.expenseType = ExpenseType.toEnum(expenseRequest.getExpenseTypeString()).getCod();
        this.category = expenseRequest.getCategory();
    }


    public ExpenseType getExpenseType() {
        return ExpenseType.toEnum(expenseType);
    }


    public void updateExpense(final ExpenseRequest expenseRequest) {
        this.description = expenseRequest.getDescription();
        this.value = expenseRequest.getValue();
        this.dueDate = expenseRequest.getDueDate();
        this.paymentDate = expenseRequest.getPaymentDate();
        this.expenseType = ExpenseType.toEnum(expenseRequest.getExpenseTypeString()).getCod();
        this.category = expenseRequest.getCategory();
    }
}
