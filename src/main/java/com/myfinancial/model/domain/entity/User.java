package com.myfinancial.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myfinancial.model.domain.enums.ProfileType;
import com.myfinancial.model.domain.request.UserRequest;
import com.myfinancial.model.security.UserSpringSecurity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends IdAbstract {

    @Length(min = 3, max = 80)
    @Column(nullable = false, length = 80)
    private String name;


    @Email
    @Column(nullable = false, unique = true)
    private String email;


    @Length(min = 6, max = 60)
    @Column(nullable = false, length = 60)
    private String password;


    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Category> categoryList = new ArrayList<>();


    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Expense> expenseList = new ArrayList<>();


    @CollectionTable
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Integer> profileList = new HashSet<>();


    public User(final UserRequest userRequest) {
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.profileList = userRequest.getProfileListSring().stream().map(
                profile -> ProfileType.toEnum(profile).getCod()).collect(Collectors.toSet()
        );
    }


    public User(final UserSpringSecurity userSpringSecurity) {
        this.id = userSpringSecurity.getId();
        this.email = userSpringSecurity.getEmail();
        this.password = userSpringSecurity.getPassword();
        this.profileList = userSpringSecurity.getAuthorities().stream().map(
                profile -> ProfileType.toEnum(profile.getAuthority()).getCod()).collect(Collectors.toSet()
        );
    }


    public Set<ProfileType> getProfiles() {
        return this.profileList.stream().map(profile -> ProfileType.toEnum(profile)).collect(Collectors.toSet());
    }


    public void updateUser(final UserRequest userRequest) {
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.profileList = userRequest.getProfileListSring().stream().map(
                profile -> ProfileType.toEnum(profile).getCod()).collect(Collectors.toSet()
        );
    }
}
