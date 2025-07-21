package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import java.util.List;

@Configuration
public class OrderConfig {
    @Bean
    public PaymentService stripe() { return new StripePaymentService(); }

    @Bean
    public PaymentService paypal() { return new PayPalPaymentService(); }

    // TO-DO: explore using Map instead of List
    @Bean
    public OrderService orderService(List<PaymentService> services) {
        return new OrderService(services);

        // ----- OLD APPROACH: RELIES ON YAML FILE -> STATIC, NOT DYNAMIC
        /*if(paymentMethod.equals("stripe"))
            return new OrderService(stripe());

        if(paymentMethod.equals("paypal"))
            return new OrderService(paypal());

        return null;*/
    }
}
