package com.ryansstore.store;

import org.springframework.beans.factory.annotation.Value;
import java.util.List;

// No longer needed with AppConfig class approach
/*import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("Stripe")
@Primary*/
public class StripePaymentService implements PaymentService {
    @Value("${payment.stripe.apiUrl}")
    private String apiURL;

    @Value("${payment.stripe.enabled}")
    private boolean enabled;

    @Value("${payment.stripe.timeOut:2500}")
    private int timeOut;

    @Value("${payment.stripe.supportedCurrencies}")
    private List<String> supportedCurrencies;

    @Override
    public void processPayment(double amount) {
        System.out.println("Welcome to Stripe!");
        System.out.println("API URL: " + apiURL);
        System.out.println("Enabled: " + enabled);
        System.out.println("timeout: " + timeOut);
        System.out.println("Currencies: " + supportedCurrencies);
        System.out.println("amount: " + amount);
    }

    public String getType() { return "stripe"; }
}
