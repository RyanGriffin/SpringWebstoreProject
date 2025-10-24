package com.ryansstore.store.authentication;

import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Data
public class JwtResponse {
    private String token;
}
