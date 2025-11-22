package com.ryansstore.store.users;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import com.ryansstore.store.common.ErrorDto;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import java.net.URI;

@Tag(name = "Users")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Registers a new user.")
    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegisterRequest request, UriComponentsBuilder uriBuilder) {
        UserDto userDto = userService.register(request);
        URI uri =  uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();

        return ResponseEntity.created(uri).body(userDto);
    }

    @Operation(summary = "Retrieves a list of all users.")
    @GetMapping
    public List<UserDto> getAllUsers(
            @Parameter(description = "Desired method of sorting. (sorts by name by default)")
            @RequestParam(name = "sort", defaultValue = "", required = false) String sort) {
        return userService.getAllUsers(sort);
    }

    @Operation(summary = "Retrieves a specific user.")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(
            @Parameter(description = "ID of the user.")
            @PathVariable Long id) {
        return userService.getUser(id);
    }

    @Operation(summary = "Updates a user's information.")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "ID of the user.")
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @Operation(summary = "Deletes a user.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user.")
            @PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @Operation(summary = "Changes a user's password.")
    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @Parameter(description = "ID of the user.")
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UserChangePasswordRequest request) {
        return userService.changePassword(id, request);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<ErrorDto> handleEmailAlreadyRegistered() {
        return ResponseEntity.badRequest().body(new ErrorDto("email is already registered!"));
    }
}
