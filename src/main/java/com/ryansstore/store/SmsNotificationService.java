package com.ryansstore.store;

// No longer needed with AppConfig class approach
/*import org.springframework.stereotype.Service;

@Service("SMS")*/
public class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) { System.out.println("Sending SMS...\nMessage: " + message); }

    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending SMS...");
        System.out.println("Phone Number: " + recipient);
        System.out.println("Message: " + message);
    }

    @Override
    public String getType() { return "sms"; }
}
