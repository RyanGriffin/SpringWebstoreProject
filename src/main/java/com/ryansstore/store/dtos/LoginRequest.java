package com.ryansstore.store.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "email is required!")
    private String email;

    @NotBlank(message = "password is required!")
    private String password;
}
