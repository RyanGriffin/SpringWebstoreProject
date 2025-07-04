package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class UserConfig {
    @Bean
    public InMemoryUserRepository inMemoryUserRepository() { return new InMemoryUserRepository(); }

    @Bean
    public UserService userService(NotificationManager notificationManager) {
        // emailService() is defined in NotificationConfig -> use EmailNotificationService as a param
        return new UserService(inMemoryUserRepository(), notificationManager);
    }
}
