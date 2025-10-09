package com.ryansstore.store.services;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import java.util.Date;

@Service
public class JwtService {
    public String generateToken(String email) {
        long expiration = 86400; // 24 hours in seconds

        return Jwts.builder()
                .setSubject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expiration))
                .signWith(Keys.hmacShaKeyFor("really_obscure_and_super_super_secret_key".getBytes()))
                .compact(); // similar to .build() for builder objects
    }
}
