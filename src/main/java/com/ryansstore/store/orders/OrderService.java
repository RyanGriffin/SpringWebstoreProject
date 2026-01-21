package com.ryansstore.store.orders;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.orders.dtos.OrderDto;
import com.ryansstore.store.users.User;
import com.ryansstore.store.users.Role;
import com.ryansstore.store.authentication.AuthService;
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

        if(user.isAdmin())
            return orderRepository.findAll()
                    .stream()
                    .map(orderMapper::toDto)
                    .toList();

        return orderRepository.getAllByCustomer(user)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public ResponseEntity<OrderDto> getOrder(Long orderId) {
        Order order = orderRepository.getOrderWithItems(orderId).orElseThrow(OrderNotFoundException::new);

        User user = authService.getCurrentUser();
        if(!order.isPlacedBy(user) && user.getRole() != Role.ADMIN)
            throw new UnauthorizedOrderException(); // could use Spring Security's AccessDeniedException here...

        return ResponseEntity.ok(orderMapper.toDto(order));
    }
}
