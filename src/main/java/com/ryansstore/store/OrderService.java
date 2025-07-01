package com.ryansstore.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// Not needed when using Config class approach
// @Service
public class OrderService {
    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) { this.paymentService = paymentService; }

    public void placeOrder() { paymentService.processPayment(10.00); }

    public void setPaymentService(PaymentService paymentService) { this.paymentService = paymentService; }
}
