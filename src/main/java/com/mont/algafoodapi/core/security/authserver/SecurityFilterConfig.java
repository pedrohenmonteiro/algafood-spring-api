package com.mont.algafoodapi.core.security.authserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilterConfig {
    
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

    return http.formLogin(Customizer.withDefaults()).build();
        
    }

    @Bean
    SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
        .anyRequest().authenticated())
        .httpBasic(httpBasic -> httpBasic.init(http));

        return http.formLogin(Customizer.withDefaults()).build();

    }

   
}
