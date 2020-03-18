package com.myfinancial.model.domain.response;

import com.myfinancial.model.domain.entity.User;
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


    public UserResponse(final User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profileListSring = user.getProfileList().stream().map(profileInt -> ProfileType.toEnum(profileInt).getName()).collect(Collectors.toSet());
    }
}
