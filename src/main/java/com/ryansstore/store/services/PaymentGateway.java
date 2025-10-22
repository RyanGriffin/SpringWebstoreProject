package com.ryansstore.store.services;

import com.ryansstore.store.entities.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
}
