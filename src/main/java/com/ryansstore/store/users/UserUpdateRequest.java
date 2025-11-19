package com.ryansstore.store.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "name is required!")
    private String name;

    @NotBlank(message = "email is required!")
    private String email;

    @NotBlank(message = "phone number is required!")
    private String phoneNumber;
}
