package com.ryansstore.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${payment.method:stripe}")
    private String paymentMethod;

    @Bean
    public PaymentService stripe() { return new StripePaymentService(); }

    @Bean
    public PaymentService paypal() { return new PayPalPaymentService(); }

    @Bean
    public OrderService orderService() {
        if(paymentMethod.equals("stripe"))
            return new OrderService(stripe());

        if(paymentMethod.equals("paypal"))
            return new OrderService(paypal());

        return null;
    }
}
