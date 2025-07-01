package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;

@Configuration
public class NotificationConfig {
    @Value("${contact.method:email}")
    private String notificationMethod;

    @Bean
    @Primary // email is the primary contact method, prevents autowiring issues (like in UserService)
    public EmailNotificationService emailService() { return new EmailNotificationService(); }

    @Bean
    public SmsNotificationService smsService() { return new SmsNotificationService(); }

    // TO-DO: make this dynamic instead of relying on yaml file
    @Bean
    public NotificationManager notificationManager() {
        if(notificationMethod.equals("email"))
            return new NotificationManager(emailService());

        if(notificationMethod.equals("sms"))
            return new NotificationManager(smsService());

        return null;
    }
}
