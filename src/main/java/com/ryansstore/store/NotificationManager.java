package com.ryansstore.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private NotificationService notificationService;

    public NotificationManager(@Qualifier("Email") NotificationService notificationService) { this.notificationService = notificationService; }

    public void sendNotification(String message) { notificationService.sendNotification(message); }

    public void sendNotification(String message, String recipient) { notificationService.sendNotification(message, recipient); }

    public void setNotificationService(NotificationService notificationService) { this.notificationService = notificationService; }
}
