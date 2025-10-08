package com.ryansstore.store.dtos;

import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "email is required!")
    @Email
    private String email;

    @NotBlank(message = "password is required!")
    private String password;
}
