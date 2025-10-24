package com.ryansstore.store.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
}
