package com.ryansstore.store.authentication;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import com.ryansstore.store.users.UserRepository;
import com.ryansstore.store.users.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long)auth.getPrincipal();

        return userRepository.findById(userId).orElse(null);
    }

    public LoginResponse login(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        Jwt accessToken = jwtService.generateAccessToken(user);
        Jwt refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponse(accessToken, refreshToken);
    }

    public Jwt refreshToken(String refreshToken) {
        Jwt refresh = jwtService.parse(refreshToken);
        if(refresh.isExpired())
            throw new BadCredentialsException("refresh token is invalid!");

        User user = userRepository.findById(refresh.getUserId()).orElseThrow();
        return jwtService.generateAccessToken(user);
    }
}
