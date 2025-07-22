package com.ryansstore.store.dtos;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
