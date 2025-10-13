package com.ryansstore.store.controllers;

import com.ryansstore.store.exceptions.CartNotFoundException;
import com.ryansstore.store.exceptions.EmptyCartException;
import com.ryansstore.store.repositories.CartRepository;
import com.ryansstore.store.services.CartService;
import com.ryansstore.store.services.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.dtos.CheckoutRequest;
import com.ryansstore.store.dtos.CheckoutResponse;
import lombok.AllArgsConstructor;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final CartService cartService;
    private final CartRepository cartRepository;

    // 2. create a new order using
    @PostMapping
    public ResponseEntity<CheckoutResponse> checkout(@Valid @RequestBody CheckoutRequest request) {
        // 1. get cart with cartId in request
        UUID cartId = request.getCartId();

        CheckoutResponse response = checkoutService.checkout(cartId);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCartNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "cart not found!"));
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<Map<String,String>> handleEmptyCartFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "cart is empty!"));
    }
}
