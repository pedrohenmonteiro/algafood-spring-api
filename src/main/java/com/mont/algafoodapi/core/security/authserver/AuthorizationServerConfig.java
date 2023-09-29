package com.mont.algafoodapi.core.security.authserver;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import com.mont.algafoodapi.domain.repository.UserRepository;



@Configuration
public class AuthorizationServerConfig {
    
  @Bean
  AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
  }



  @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(UserRepository userRepository) {
        return context -> {
            Authentication authentication = context.getPrincipal();
            if (authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();

                com.mont.algafoodapi.domain.model.User userDomain = userRepository.findByEmail(user.getUsername()).orElseThrow();

                Set<String> authorities = new HashSet<>();
                for (GrantedAuthority authority : user.getAuthorities()) {
                    authorities.add(authority.getAuthority());
                }

                context.getClaims().claim("user_id", userDomain.getId().toString());
                context.getClaims().claim("user_name", userDomain.getName());
                
                context.getClaims().claim("authorities", authorities);
            }
        };
    }
}
