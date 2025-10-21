package com.ryansstore.store.services;

import com.ryansstore.store.entities.Order;
import com.ryansstore.store.entities.User;
import org.springframework.stereotype.Service;
import com.ryansstore.store.mappers.OrderMapper;
import com.ryansstore.store.repositories.OrderRepository;
import com.ryansstore.store.dtos.OrderDto;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private AuthService authService;
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        User user = authService.getCurrentUser();
        List<Order> orders = orderRepository.getAllByCustomer(user);

        return orders.stream().map(orderMapper::toDto).toList();
    }
}
