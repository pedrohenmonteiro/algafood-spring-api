package com.mont.algafoodapi.core.security.authserver;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import lombok.Getter;

@Getter
public class AuthUser extends User {

    private String fullName;
    
    public AuthUser(com.mont.algafoodapi.domain.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);

        this.fullName = user.getName();
    }
}
