package com.ryansstore.store.users;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class UserChangePasswordRequest {
    @NotBlank(message = "old password is required!")
    private String oldPassword;

    @NotBlank(message = "new password is required!")
    @Size(min = 6, max = 25, message = "new password must be between 6 and 25 characters!")
    private String newPassword;
}
