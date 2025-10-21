package com.ryansstore.store.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.exceptions.CartNotFoundException;
import com.ryansstore.store.exceptions.EmptyCartException;
import com.ryansstore.store.services.CheckoutService;
import com.ryansstore.store.dtos.ErrorDto;
import com.ryansstore.store.dtos.CheckoutRequest;
import com.ryansstore.store.dtos.CheckoutResponse;
import lombok.AllArgsConstructor;
import jakarta.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<CheckoutResponse> checkout(@Valid @RequestBody CheckoutRequest request) {
        return ResponseEntity.ok(checkoutService.checkout(request));
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("cart not found!"));
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<ErrorDto> handleEmptyCartFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("cart is empty!"));
    }
}
