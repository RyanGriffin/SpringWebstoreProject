package com.ryansstore.store.services;

import com.ryansstore.store.entities.User;
import io.jsonwebtoken.Claims;
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

    public String generateAccessToken(User user) {
        long expiration = 300; // 5 minutes in seconds

        return generateToken(user, expiration);
    }

    public String generateRefreshToken(User user) {
        long expiration = 604800; // 7 days in seconds

        return generateToken(user, expiration);
    }

    private String generateToken(User user, long expiration) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            var claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        }
        catch (JwtException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    public Long getUserIdFromToken(String token) {
        return Long.valueOf(getClaims(token).getSubject());
    }

    public String getNameFromToken(String token) {
        return getClaims(token).get("name").toString();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).get("email").toString();
    }
}
