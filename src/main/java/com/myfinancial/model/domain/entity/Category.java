package com.myfinancial.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myfinancial.model.domain.request.CategoryRequest;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends IdAbstract {

    @NotNull
    @Length(min = 3, max = 40)
    @Column(nullable = false, length = 40)
    private String name;


    @NotNull
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private User user;


    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Expense> expenseList = new ArrayList<>();


    public Category(final CategoryRequest categoryRequest) {
        this.name = categoryRequest.getName();
    }


    public void updateCategory(final CategoryRequest categoryRequest) {
        this.name = categoryRequest.getName();
    }
}
