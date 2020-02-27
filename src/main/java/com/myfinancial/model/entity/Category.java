package com.myfinancial.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends IdAbstract {

    @Column(nullable = false)
    private String name;


    @ManyToOne
    @ToString.Exclude
    private User user;


    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private List<Expense> expenseList = new ArrayList<>();
}
