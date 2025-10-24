package com.ryansstore.store.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtConfig {
    private String secret;
    private int accessTokenExpiration;
    private int refreshTokenExpiration;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}


