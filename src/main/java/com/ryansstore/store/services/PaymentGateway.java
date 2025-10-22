package com.ryansstore.store.services;

import com.ryansstore.store.entities.Order;

public interface PaymentGateway {
    public CheckoutSession createCheckoutSession(Order order);
}
