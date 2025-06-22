package com.ryansstore.store;

public interface NotificationService {
    void sendNotification(String message);
    void sendNotification(String message, String recipient);
}
