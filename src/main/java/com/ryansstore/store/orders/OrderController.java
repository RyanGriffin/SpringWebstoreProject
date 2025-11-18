package com.ryansstore.store.orders;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.orders.dtos.OrderDto;
import com.ryansstore.store.common.ErrorDto;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Orders")
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrderNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("order not found!"));
    }

    @ExceptionHandler(UnauthorizedOrderException.class)
    public ResponseEntity<ErrorDto> handleUnauthorizedOrder() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDto("you can't view this order! You aren't an admin and this isn't your order!"));
    }
}
