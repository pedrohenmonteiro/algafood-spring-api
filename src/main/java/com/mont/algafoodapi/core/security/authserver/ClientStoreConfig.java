package com.mont.algafoodapi.core.security.authserver;

import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

@Configuration
public class ClientStoreConfig {


    
    @Bean
    RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
        RegisteredClient backClient = RegisteredClient
            .withId(UUID.randomUUID().toString())
            .clientId("back-server")
            .clientSecret("{noop}back")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .scope("read")
            .scope("write")
            .tokenSettings(TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofMinutes(15))
                .refreshTokenTimeToLive(Duration.ofDays(15))
                .reuseRefreshTokens(false)
                .build()
            )
            .build();

            RegisteredClient frontClient = RegisteredClient
            .withId(UUID.randomUUID().toString())
            .clientId("front-server")
            .clientSecret(passwordEncoder.encode("front"))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)   
            .redirectUri("https://oidcdebugger.com/debug")         
            .scope("read")
            .scope("write")
            .tokenSettings(TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofDays(30))
                .refreshTokenTimeToLive(Duration.ofDays(30))
                .reuseRefreshTokens(false)
                .build()
            )
            .clientSettings(ClientSettings.builder()
                .requireAuthorizationConsent(true).build())
            .build();

        return new InMemoryRegisteredClientRepository(Arrays.asList(backClient, frontClient));
    }
}
