package com.ryansstore.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.jwt")
@Configuration
public class JwtConfig {
    private String secret;
    private int accessTokenExpiration;
    private int refreshTokenExpiration;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}


