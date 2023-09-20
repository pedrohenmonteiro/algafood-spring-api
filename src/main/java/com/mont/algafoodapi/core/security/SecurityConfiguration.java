package com.mont.algafoodapi.core.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableWebSecurity
public class SecurityConfiguration {
    

// @Bean
//   @Order(Ordered.HIGHEST_PRECEDENCE)
//   SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//     // OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

//     return http.build();
//   }

 
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
      throws Exception {
    http.authorizeHttpRequests((authorize) -> authorize
            .anyRequest().authenticated())
            .httpBasic(httpBasic -> httpBasic.init(http));
        
    http.csrf(crsf -> crsf.disable());
     

    return http.build();
  }
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(
            User
            .withUsername("pedro")
            .password("{noop}password")
            .roles("USER")
            .build()
        );

        userDetailsManager.createUser(
            User
            .withUsername("lucas")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build()

        );

        return userDetailsManager;
    }

  /* Apenas constroi o AS com as configurações desejadas. */
  @Bean
  AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
  }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
