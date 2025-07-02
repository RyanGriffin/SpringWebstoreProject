package com.ryansstore.store;

// No longer needed with AppConfig class approach
/*import org.springframework.stereotype.Service;

@Service("PayPal")*/
public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Welcome to PayPal!");
        System.out.println("amount: " + amount);
    }

    @Override
    public String getType() { return "paypal"; }
}
