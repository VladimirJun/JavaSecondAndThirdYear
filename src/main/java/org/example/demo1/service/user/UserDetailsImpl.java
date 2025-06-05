package org.example.demo1.service.user;

import org.example.demo1.entity.user.Role;
import org.example.demo1.entity.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final UserEntity user;

    public UserDetailsImpl(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public Role getRole() {
        return user.getRole();
    }

    public Long getId() {
        return user.getId();
    }

    public Long getProfileId() {
        return user.getProfileId();
    }
}