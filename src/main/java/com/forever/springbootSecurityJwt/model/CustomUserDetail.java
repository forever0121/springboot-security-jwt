package com.forever.springbootSecurityJwt.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetail implements UserDetails {
    private String username;
    private String password;
    private int id;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    public int getId() {
        return this.id;
    }

    public CustomUserDetail(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoleList()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        this.authorities = authorities;
    }
}
