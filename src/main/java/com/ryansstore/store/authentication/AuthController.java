package com.ryansstore.store.authentication;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import com.ryansstore.store.users.User;
import com.ryansstore.store.users.UserDto;
import com.ryansstore.store.users.UserNotFoundException;
import jakarta.validation.Valid;
import com.ryansstore.store.users.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

@Tag(name = "Auth")
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtConfig jwtConfig;
    private final UserMapper userMapper;

    @Operation(summary = "Logs in a user.")
    @PostMapping("/login")
    public JwtResponse login(
            @Parameter(description = "Request DTO containing an email and password.")
            @Valid @RequestBody LoginRequest loginRequest,
            @Parameter(description = "HTTP response containing the refresh token cookie.")
            HttpServletResponse response) {
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

    @Operation(summary = "Generates a new access token using the refresh token.")
    @PostMapping("/refresh")
    public JwtResponse refresh(
            @Parameter(description = "Refresh token used for generating new access token")
            @CookieValue(value = "refreshToken") String refreshToken) {
        return new JwtResponse(authService.refreshToken(refreshToken).toString());
    }

    @Operation(summary = "Returns a response containing the details of the current user.")
    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        User user =  authService.getCurrentUser();
        if(user == null)
            throw new UserNotFoundException();

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}


