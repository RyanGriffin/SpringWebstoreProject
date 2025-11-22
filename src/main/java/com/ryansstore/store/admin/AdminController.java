package com.ryansstore.store.admin;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Admin")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Operation(summary = "Verifies that the current user has admin privileges.")
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Admin!";
    }
}


