package com.ryansstore.store;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// No longer needed with AppConfig class approach
/*import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service*/
public class OrderService {
    private List<PaymentService> paymentServices;

    public OrderService(List<PaymentService> services) {
        paymentServices = services;

        // TO-DO: Explore using Map instead of List
        /*
        paymentServices = services.stream()
                 .collect(Collectors.toMap(s -> s.getType(), Function.identity()));
         */
    }

    public void placeOrder(String method, double price) {
        for(PaymentService service : paymentServices) {
            if(service.getType().equalsIgnoreCase(method)) {
                service.processPayment(price);
                return;
            }
        }

        throw new IllegalArgumentException("Unsupported payment method: " + method);
    }

    // ----- OLD METHOD: RELIES ON YAML FILE -> STATIC, NOT DYNAMIC
    /*
    private PaymentService paymentService; // ----- OLD METHOD: RELIES ON YAML FILE -> STATIC, NOT DYNAMIC

    public OrderService(PaymentService paymentService) { this.paymentService = paymentService; }

    public void placeOrder() { paymentService.processPayment(10.00); }
    public void setPaymentService(PaymentService paymentService) { this.paymentService = paymentService; }
     */
}
