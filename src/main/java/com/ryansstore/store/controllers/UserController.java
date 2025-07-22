package com.ryansstore.store.controllers;

import com.ryansstore.store.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.dtos.UserDto;
import com.ryansstore.store.repositories.UserRepository;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // method: GET
    @GetMapping // similar to @RequestMapping
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

        // This is how we would pass a DTO without using a mapper
        // UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber());
        // return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto data) {
        return data;
    }
}
