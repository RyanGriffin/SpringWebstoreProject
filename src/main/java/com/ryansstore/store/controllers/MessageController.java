package com.ryansstore.store.controllers;

import com.ryansstore.store.entities.Message;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Messages")
@RestController
public class MessageController {
    @RequestMapping("/hello")
    public Message sayHello() { return new Message("Hello World!"); }
}
