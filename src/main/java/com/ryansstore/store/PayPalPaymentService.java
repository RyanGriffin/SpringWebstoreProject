package com.ryansstore.store;

import org.springframework.stereotype.Service;

// @Service("PayPal")
public class PayPalPaymentService implements PaymentService {
    public void processPayment(double amount) {
        System.out.println("Welcome to PayPal!");
        System.out.println("amount: " + amount);
    }
}
