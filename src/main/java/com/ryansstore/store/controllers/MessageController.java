package com.ryansstore.store.controllers;

import com.ryansstore.store.entities.Message;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MessageController {
    @RequestMapping("/hello")
    public Message sayHello() { return new Message("Hello World!"); }
}
