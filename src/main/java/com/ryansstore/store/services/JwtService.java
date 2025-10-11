package com.ryansstore.store.services;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.config.JwtConfig;
import lombok.AllArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig config;

    public String generateAccessToken(User user) {
        return generateToken(user, config.getAccessTokenExpiration());
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, config.getRefreshTokenExpiration());
    }

    private String generateToken(User user, long expiration) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expiration))
                .signWith(config.getSecretKey())
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
                .verifyWith(config.getSecretKey())
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
