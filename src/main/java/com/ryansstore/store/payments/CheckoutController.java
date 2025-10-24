package com.ryansstore.store.payments;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.dtos.ErrorDto;
import com.ryansstore.store.carts.CartNotFoundException;
import com.ryansstore.store.carts.EmptyCartException;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
            return checkoutService.checkout(request);
    }

    @PostMapping("/webhook")
    public void handleWebhook(@RequestHeader Map<String, String> headers, @RequestBody String payload) {
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("cart not found!"));
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<ErrorDto> handleEmptyCartFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("cart is empty!"));
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorDto> handlePaymentException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("error while creating new checkout session!"));
    }
}
