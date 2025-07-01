package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class OrderConfig {
    @Value("${payment.method:paypal}")
    private String paymentMethod;

    @Bean
    public PaymentService stripe() { return new StripePaymentService(); }

    @Bean
    public PaymentService paypal() { return new PayPalPaymentService(); }

    // TO-DO: make this dynamic instead of relying on yaml file
    @Bean
    public OrderService orderService() {
        if(paymentMethod.equals("stripe"))
            return new OrderService(stripe());

        if(paymentMethod.equals("paypal"))
            return new OrderService(paypal());

        return null;
    }
}
