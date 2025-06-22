package com.ryansstore.store;

import org.springframework.stereotype.Service;

@Service("SMS")
public class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }

    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending SMS...\nRecipient: " + recipient + "\nMessage: " + message);
    }
}
