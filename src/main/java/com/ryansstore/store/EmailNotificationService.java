package com.ryansstore.store;

import org.springframework.beans.factory.annotation.Value;

// No longer needed with AppConfig class approach
/*import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("Email")
@Primary*/
public class EmailNotificationService implements NotificationService {
    @Value("${contact.emailPort}")
    private String emailPort;

    @Value("${contact.emailHost}")
    private String emailHost;

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email...");
        System.out.println("Email Host: " + emailHost);
        System.out.println("Email Port: " + emailPort);
        System.out.println("Message: " + message);
    }

    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending Email...");
        System.out.println("Email Host: " + emailHost);
        System.out.println("Email Port: " + emailPort);
        System.out.println("Email Address: " + recipient);
        System.out.println("Message: " + message);
    }
}
