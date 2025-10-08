package com.ryansstore.store.controllers;

import com.ryansstore.store.dtos.LoginRequest;
import com.ryansstore.store.repositories.UserRepository;
import com.ryansstore.store.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        if(authService.loginSuccessful(loginRequest.getEmail(), loginRequest.getPassword()))
            return ResponseEntity.ok().build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}


