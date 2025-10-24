package com.ryansstore.store.users;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private String email;
    private String phoneNumber;
}
