package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;

@Configuration
public class OrderConfig {
    @Bean
    public PaymentService stripe() { return new StripePaymentService(); }

    @Bean
    public PaymentService paypal() { return new PayPalPaymentService(); }

    // TO-DO: make this dynamic instead of relying on yaml file
    @Bean
    public OrderService orderService(List<PaymentService> services) {
        return new OrderService(services);

        // ----- OLD METHOD: RELIES ON YAML FILE -> STATIC, NOT DYNAMIC
        /*if(paymentMethod.equals("stripe"))
            return new OrderService(stripe());

        if(paymentMethod.equals("paypal"))
            return new OrderService(paypal());

        return null;*/
    }
}
