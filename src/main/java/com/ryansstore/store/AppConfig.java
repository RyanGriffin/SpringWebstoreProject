package com.ryansstore.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Value("${payment.method:paypal}")
    private String paymentMethod;

    @Value("${contact.method:email}")
    private String notificationMethod;

    @Bean
    public PaymentService stripe() { return new StripePaymentService(); }

    @Bean
    public PaymentService paypal() { return new PayPalPaymentService(); }

    // uses
    @Bean
    public OrderService orderService() {
        if(paymentMethod.equals("stripe"))
            return new OrderService(stripe());

        if(paymentMethod.equals("paypal"))
            return new OrderService(paypal());

        return null;
    }

    @Bean
    @Primary
    public EmailNotificationService emailService() { return new EmailNotificationService(); }

    @Bean
    public SmsNotificationService smsService() { return new SmsNotificationService(); }

    @Bean
    public NotificationManager notificationManager() {
        if(notificationMethod.equals("email"))
            return new NotificationManager(emailService());

        if(notificationMethod.equals("sms"))
            return new NotificationManager(smsService());

        return null;
    }
}
