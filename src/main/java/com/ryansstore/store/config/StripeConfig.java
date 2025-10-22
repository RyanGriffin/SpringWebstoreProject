package com.ryansstore.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;

@Configuration
public class StripeConfig {
    @Value("${stripe.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
}
