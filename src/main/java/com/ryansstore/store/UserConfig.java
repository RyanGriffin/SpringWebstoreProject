package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class UserConfig {
    @Bean
    public InMemoryUserRepository inMemoryUserRepository() { return new InMemoryUserRepository(); }

    @Bean
    public UserService userService(EmailNotificationService emailNotificationService) {
        // confirmation notifs only via email -> explicitly pass in an email object
        // emailService() is defined in NotificationConfig -> use EmailNotificationService as a param
        return new UserService(inMemoryUserRepository(), emailNotificationService);
    }
}
