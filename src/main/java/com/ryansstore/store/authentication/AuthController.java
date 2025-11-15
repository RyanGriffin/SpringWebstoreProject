package com.ryansstore.store.authentication;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import com.ryansstore.store.users.UserMapper;
import com.ryansstore.store.users.UserDto;
import com.ryansstore.store.users.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;

@AllArgsConstructor
@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtConfig jwtConfig;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(loginRequest);

        Jwt accessToken = loginResponse.getAccessToken();

        Jwt refreshToken = loginResponse.getRefreshToken();
        Cookie cookie = new Cookie("refreshToken", refreshToken.toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new JwtResponse(accessToken.toString());
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@CookieValue(value = "refreshToken") String refreshToken) {
        return new JwtResponse(authService.refreshToken(refreshToken).toString());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        User user =  authService.getCurrentUser();
        if(user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}


