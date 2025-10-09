package com.ryansstore.store.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import java.util.Date;

@Service
public class JwtService {
    @Value("${spring.jwt.secret}")
    private String secret;

    public boolean validateToken(String token) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getPayload();

            return claims.getExpiration().after(new Date());
        }
        catch (JwtException e) {
            return false;
        }
    }

    public String generateToken(String email) {
        long expiration = 86400; // 24 hours in seconds

        return Jwts.builder()
                .setSubject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact(); // similar to .build() for builder objects
    }
}
