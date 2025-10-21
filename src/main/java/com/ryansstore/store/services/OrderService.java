package com.ryansstore.store.services;

import org.springframework.stereotype.Service;
import com.ryansstore.store.exceptions.OrderNotFoundException;
import com.ryansstore.store.exceptions.UnauthorizedOrderException;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.entities.Order;
import com.ryansstore.store.dtos.OrderDto;
import com.ryansstore.store.repositories.OrderRepository;
import com.ryansstore.store.mappers.OrderMapper;
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

    public ResponseEntity<OrderDto> getOrder(Long orderId) {
        Order order = orderRepository.getOrderWithItems(orderId).orElseThrow(OrderNotFoundException::new);

        User user = authService.getCurrentUser();
        if(!order.isPlacedBy(user))
            throw new UnauthorizedOrderException(); // could use Spring Security's AccessDeniedException here...

        return ResponseEntity.ok(orderMapper.toDto(order));
    }
}
