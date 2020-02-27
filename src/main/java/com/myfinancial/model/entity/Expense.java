package com.myfinancial.model.entity;

import com.myfinancial.model.enums.ExpenseType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Expense extends IdAbstract {

    @Column(nullable = false)
    private String description;


    @Column(nullable = false)
    private Double value;


    @Column(nullable = false)
    private int expenseType;


    @ManyToOne
    private User user;


    @ManyToOne
    private Category category;


    public ExpenseType getExpenseType() {
        return ExpenseType.toEnum(expenseType);
    }
}
