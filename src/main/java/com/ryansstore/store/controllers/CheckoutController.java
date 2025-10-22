package com.ryansstore.store.controllers;

import com.ryansstore.store.dtos.CheckoutResponse;
import com.ryansstore.store.entities.Order;
import com.ryansstore.store.entities.OrderStatus;
import com.ryansstore.store.repositories.OrderRepository;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.StripeObject;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ryansstore.store.services.CheckoutService;
import com.ryansstore.store.dtos.ErrorDto;
import com.ryansstore.store.dtos.CheckoutRequest;
import com.ryansstore.store.exceptions.CartNotFoundException;
import com.ryansstore.store.exceptions.EmptyCartException;
import com.ryansstore.store.exceptions.PaymentException;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final OrderRepository orderRepository;

    @Value("${stripe.webhookSecretKey}")
    private String webhookSecretKey;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
            return checkoutService.checkout(request);
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> handleWebhook(
                @RequestHeader("Stripe-Signature") String signature,
                @RequestBody String payload
    ) {
        try {
            Event event =  Webhook.constructEvent(payload, signature, webhookSecretKey);
            System.out.println(event.getType());

            // if -> charge event: cast stripeObject to Charge object
            // if -> payment_intent.succeeded: cast stripeObject to PaymentIntent object
            // note: when handling this event, we should check eventId hasn't been handled before
            // note: we should store this info somewhere (db, cache, etc.), but will deal with that later
            // note: may consider this out-of-scope for this project...
            StripeObject stripeObject = event.getDataObjectDeserializer().getObject().orElse(null);

            switch(event.getType()) {
                case "payment_intent.succeeded" -> {
                    PaymentIntent paymentIntent = (PaymentIntent) stripeObject;

                    if(paymentIntent != null) {
                        String orderId = paymentIntent.getMetadata().get("order_id");

                        Order order = orderRepository.findById(Long.valueOf(orderId)).orElseThrow();
                        order.setStatus(OrderStatus.PAID);
                        orderRepository.save(order);
                    }
                }
                case "payment_intent.failed" -> {
                    // update order status to FAILED
                }
            }

            return ResponseEntity.ok().build();

        } catch (SignatureVerificationException e) {
            return ResponseEntity.badRequest().build();
        }
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
