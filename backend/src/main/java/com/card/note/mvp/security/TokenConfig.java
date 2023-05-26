package com.card.note.mvp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class TokenConfig {
   
    @Value("${security.authentication.jwt.base64-secret}")
    private String secret;
 
    @Value("${security.authentication.jwt.token-validity-in-seconds}")
    private Long validityInSeconds;
 
    @Value("${security.authentication.jwt.token-validity-in-seconds-for-remember-me}")
    private Long rememberSeconds;

    public int getTokenValidityInSecondsForRememberMe() {
        return 0;
    }
}
