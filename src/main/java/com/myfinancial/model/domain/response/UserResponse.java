package com.myfinancial.model.domain.response;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.enums.ProfileType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Set<String> profileListSring;


    public UserResponse(final Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.profileListSring = customer.getProfileList().stream().map(profileInt -> ProfileType.toEnum(profileInt).getName()).collect(Collectors.toSet());
    }
}
