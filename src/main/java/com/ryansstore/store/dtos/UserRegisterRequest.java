package com.ryansstore.store.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

@Data
public class UserRegisterRequest {
    @NotBlank(message = "name is required!") // ensures string isn't empty or whitespace
    @Size(max = 255, message = "name must be less than 255 characters")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    private String email;

    @NotBlank(message = "phone number is required")
    @Size(min = 13, max = 13, message = "must be a full phone number - i.e. '(555)555-5555'")
    private String phoneNumber;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 25, message = "password must be between 6 and 25 characters")
    private String password;
}
