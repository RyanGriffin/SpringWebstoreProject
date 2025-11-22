package com.ryansstore.store.orders;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.orders.dtos.OrderDto;
import com.ryansstore.store.common.ErrorDto;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

@Tag(name = "Orders")
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Returns a response containing a list of every order.")
    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "Returns a response containing a specific order.")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(
            @Parameter(description = "ID of the order.")
            @PathVariable("orderId") Long orderId) {
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
