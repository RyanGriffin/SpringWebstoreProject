package com.ryansstore.store;

public interface PaymentService {
    void processPayment(double amount);
    String getType();
}
