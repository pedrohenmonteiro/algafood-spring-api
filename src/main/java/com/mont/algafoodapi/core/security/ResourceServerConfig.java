package com.mont.algafoodapi.core.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {
    

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
      throws Exception {
    http.authorizeHttpRequests((authorize) -> authorize
            .anyRequest().authenticated());
        
    http.oauth2ResourceServer(oauth2 -> oauth2
    .opaqueToken(opaqueToken -> opaqueToken
        .introspectionUri("http://localhost:9090/oauth2/introspection")
        .introspectionClientCredentials("client-server", "secret")
    )
); 

    return http.build();
  }
    
}
