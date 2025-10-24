package com.ryansstore.store.authentication;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import com.ryansstore.store.users.User;
import lombok.AllArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig config;

    public Jwt generateAccessToken(User user) {
        return generateToken(user, config.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user) {
        return generateToken(user, config.getRefreshTokenExpiration());
    }

    private Jwt generateToken(User user, long expiration) {
        Claims claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("name", user.getName())
                .add("email", user.getEmail())
                .add("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expiration))
                .build();

        return new Jwt(claims, config.getSecretKey());
    }

    public Jwt parse(String token) {
        try {
            Claims claims = getClaims(token);

            return new Jwt(claims, config.getSecretKey());
        } catch (JwtException e) {
            return null;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(config.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

//    public Long getUserIdFromToken(String token) {
//        return Long.valueOf(getClaims(token).getSubject());
//    }
//
//    public String getNameFromToken(String token) {
//        return getClaims(token).get("name").toString();
//    }
//
//    public String getEmailFromToken(String token) {
//        return getClaims(token).get("email").toString();
//    }
//
//    public Role getRoleFromToken(String token) {
//        return Role.valueOf(getClaims(token).get("role").toString());
//    }
}
