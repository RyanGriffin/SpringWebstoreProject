package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import java.util.List;

@Configuration
public class NotificationConfig {
    @Bean
    @Primary // email is the primary contact method, prevents autowiring issues (like in UserService)
    public EmailNotificationService emailService() { return new EmailNotificationService(); }

    @Bean
    public SmsNotificationService smsService() { return new SmsNotificationService(); }

    // TO-DO: make this dynamic instead of relying on yaml file
    @Bean
    public NotificationManager notificationManager(List<NotificationService> services) {
        return new NotificationManager(services);

        // ----- OLD METHOD: RELIES ON YAML FILE -> STATIC, NOT DYNAMIC
        /*
        if(notificationMethod.equals("email"))
            return new NotificationManager(emailService());

        if(notificationMethod.equals("sms"))
            return new NotificationManager(smsService());

        return null;
        */
    }
}
