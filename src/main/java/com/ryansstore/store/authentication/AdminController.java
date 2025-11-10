package com.ryansstore.store.authentication;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Admin!";
    }
}


