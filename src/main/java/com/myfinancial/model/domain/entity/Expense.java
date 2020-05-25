package com.myfinancial.model.domain.entity;

import com.myfinancial.model.domain.enums.ExpenseType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expense")
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

    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
