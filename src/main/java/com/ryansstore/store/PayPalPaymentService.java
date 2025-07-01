package com.ryansstore.store;

import org.springframework.stereotype.Service;

// Not needed when using Config class approach
// @Service("PayPal")
public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Welcome to PayPal!");
        System.out.println("amount: " + amount);
    }
}
