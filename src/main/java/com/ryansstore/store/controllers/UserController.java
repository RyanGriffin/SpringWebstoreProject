package com.ryansstore.store.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.dtos.UserDto;
import com.ryansstore.store.dtos.UserRegisterRequest;
import com.ryansstore.store.dtos.UserUpdateRequest;
import com.ryansstore.store.dtos.UserChangePasswordRequest;
import com.ryansstore.store.mappers.UserMapper;
import com.ryansstore.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegisterRequest request, UriComponentsBuilder uriBuilder) {
        User newUser = userMapper.toEntity(request);

        userRepository.save(newUser);

        UserDto userDto = userMapper.toDto(newUser);
        URI uri =  uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();

        return ResponseEntity.created(uri).body(userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(name = "sort", defaultValue = "", required = false) String sort) {
        if(!Set.of("name", "email").contains(sort)) // if parameter isn't valid...
            sort = "name"; // ...set to default value (name in this case)

        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserUpdateRequest request) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) // return 404 if user doesn't exist
            return ResponseEntity.notFound().build();

        userMapper.updateEntity(request, user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) // return 404 if user doesn't exist
            return ResponseEntity.notFound().build();

        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable(name = "id") Long id, @RequestBody UserChangePasswordRequest request) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) // return 404 if user doesn't exist
            return ResponseEntity.notFound().build();
        if(!user.getPassword().equals(request.getOldPassword())) // return UNAUTHORIZED if passwords don't match
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }
}
