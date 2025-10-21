package com.ryansstore.store.controllers;

import com.ryansstore.store.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ryansstore.store.repositories.OrderRepository;
import com.ryansstore.store.mappers.OrderMapper;
import com.ryansstore.store.entities.Order;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.dtos.OrderDto;
import com.ryansstore.store.services.AuthService;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }
}
