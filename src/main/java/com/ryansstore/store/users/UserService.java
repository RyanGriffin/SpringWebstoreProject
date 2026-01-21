package com.ryansstore.store.users;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Sort;
import com.ryansstore.store.authentication.AuthService;
import lombok.AllArgsConstructor;
import java.util.Set;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final AuthService authService;

    public UserDto register(UserRegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new EmailAlreadyRegisteredException();

        User newUser = userMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(Role.USER);
        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }

    public List<UserDto> getAllUsers(String sort) {
        if(!Set.of("id", "name", "email").contains(sort))
            sort = "name";

        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public ResponseEntity<UserDto> getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    public ResponseEntity<UserDto> updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if(userRepository.existsByEmail(request.getEmail()) && !user.getEmail().equals(request.getEmail()))
            throw new EmailAlreadyRegisteredException();

        userMapper.updateEntity(request, user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    public ResponseEntity<Void> changePassword(Long id, UserChangePasswordRequest request) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), request.getOldPassword())
        );

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        User currentUser = authService.getCurrentUser();
        if(!currentUser.getId().equals(id) && !currentUser.isAdmin())
            throw new UserUnauthorizedDeleteException();

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }
}
