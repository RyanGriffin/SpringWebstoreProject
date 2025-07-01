package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class UserConfig {
    @Bean
    public InMemoryUserRepository inMemoryUserRepository() { return new InMemoryUserRepository(); }

    @Bean
    public UserService userService(EmailNotificationService emailNotificationService) {
        // explicitly pass in 'emailService' because we only send confirmation emails, not sms
        // must use EmailNotificationService as a param, emailService() is defined in NotificationConfig, not here
        return new UserService(inMemoryUserRepository(), emailNotificationService);
    }
}
