package com.ryansstore.store.services;

import com.ryansstore.store.entities.User;
import com.ryansstore.store.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;

    public boolean loginSuccessful(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);

        return user != null && BCrypt.checkpw(password, user.getPassword());
    }
}
