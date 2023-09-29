package com.mont.algafoodapi.core.security.authserver;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mont.algafoodapi.domain.model.User;


@Service
public class JpaUserDetailsService implements UserDetailsService {
    
    @Autowired
    private com.mont.algafoodapi.domain.repository.UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("E-mail not found"));

        return new AuthUser(user, getAuthorities(user));
    }


    private Collection<GrantedAuthority> getAuthorities(User user) {
        return user.getGroups().stream()
                .flatMap(group -> group.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName().toUpperCase()))
                .collect(Collectors.toSet());
                        
    }
}
