package com.myfinancial.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "category")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends IdAbstract {

    @NotNull
    @NonNull
    @Length(min = 3, max = 40)
    @Column(nullable = false, length = 40)
    private String name;


    @NotNull
    @NonNull
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Expense> expenseList = new ArrayList<>();
}
