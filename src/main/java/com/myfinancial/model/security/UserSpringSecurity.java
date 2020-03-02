package com.myfinancial.model.security;

import com.myfinancial.model.domain.enums.ProfileType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserSpringSecurity implements UserDetails {

    private Long id;

    private String email;

    private String password;

    public Collection<? extends GrantedAuthority> authorities;


    public UserSpringSecurity(final Long id, final String email, final String password, final Set<ProfileType> profiles) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profiles.stream().map(profile -> new SimpleGrantedAuthority(profile.getName())).collect(Collectors.toList());
    }


    public boolean hasRole(final ProfileType profile) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getName()));
    }


    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
