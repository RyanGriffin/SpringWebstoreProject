package com.ryansstore.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class PojoUserConfig {
    @Bean
    public InMemoryUserRepository inMemoryUserRepository() { return new InMemoryUserRepository(); }

    @Bean(name = "oldUserService")
    public PojoUserService userService(NotificationManager notificationManager) {
        // emailService() is defined in NotificationConfig -> use EmailNotificationService as a param
        return new PojoUserService(inMemoryUserRepository(), notificationManager);
    }
}
