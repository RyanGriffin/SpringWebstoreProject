package com.ryansstore.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("Email")
@Primary
public class EmailNotificationService implements NotificationService {
    @Value("${contact.emailPort}")
    private String emailPort;

    @Value("${contact.emailHost}")
    private String emailHost;

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email: " + message);
    }

    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending Email...\nEmail Host: " + emailHost + "\nEmail Port: " + emailPort + "\nRecipient: " + recipient + "\nMessage: " + message);
    }
}
