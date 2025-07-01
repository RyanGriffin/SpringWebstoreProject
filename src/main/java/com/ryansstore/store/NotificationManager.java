package com.ryansstore.store;

// No longer needed with AppConfig class approach
/*import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service*/
public class NotificationManager {
    private NotificationService notificationService;

    public NotificationManager(NotificationService notificationService) { this.notificationService = notificationService; }

    // old approach (uses qualifier instead of handling notifServ choice in code)
    // public NotificationManager(@Qualifier("Email") NotificationService notificationService) { this.notificationService = notificationService; }

    public void sendNotification(String message) { notificationService.sendNotification(message); }

    public void sendNotification(String message, String recipient) { notificationService.sendNotification(message, recipient); }

    public void setNotificationService(NotificationService notificationService) { this.notificationService = notificationService; }
}
