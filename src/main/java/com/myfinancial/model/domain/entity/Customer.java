package com.myfinancial.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myfinancial.model.domain.enums.ProfileType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "customer")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends IdAbstract {

    @NonNull
    @Length(min = 3, max = 80)
    @Column(nullable = false, length = 80)
    private String name;


    @Email
    @NonNull
    @Column(nullable = false, unique = true)
    private String email;


    @NonNull
    @Length(min = 6, max = 60)
    @Column(nullable = false, length = 60)
    private String password;


    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private ProfileType profileType;


    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Category> categoryList = new ArrayList<>();


    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Expense> expenseList = new ArrayList<>();
}
