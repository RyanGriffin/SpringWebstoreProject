package com.ryansstore.store;

import java.util.List;

// No longer needed with AppConfig class approach
/*import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service*/
public class NotificationManager {
    private List<NotificationService> notificationServices;

    public NotificationManager(List<NotificationService> services) { this.notificationServices = services; }

    public void sendNotification(String method, String message) {
        for(NotificationService service : notificationServices) {
            if(service.getType().equalsIgnoreCase(method)) {
                service.sendNotification(message);
                return;
            }
        }

        throw new IllegalArgumentException("ERROR: Unsupported notification method: " + method);
    }

    public void sendNotification(String method, String message, String recipient) {
        for(NotificationService service : notificationServices) {
            if(service.getType().equalsIgnoreCase(method)) {
                service.sendNotification(message, recipient);
                return;
            }
        }

        throw new IllegalArgumentException("ERROR: Unsupported notification method: " + method);
    }

    // ----- OLD METHOD: RELIES ON YAML FILE -> STATIC, NOT DYNAMIC
    /*
    private NotificationService notificationService;

    public NotificationManager(NotificationService notificationService) { this.notificationService = notificationService; }

    // old approach (uses qualifier instead of handling notifServ choice in code)
    // public NotificationManager(@Qualifier("Email") NotificationService notificationService) { this.notificationService = notificationService; }

    public void sendNotification(String message) { notificationService.sendNotification(message); }

    public void sendNotification(String message, String recipient) { notificationService.sendNotification(message, recipient); }

    // UNUSED: example of setter injection, this project uses constructor injection instead
    public void setNotificationService(NotificationService notificationService) { this.notificationService = notificationService; }
     */
}
