package com.mounirgaiby.notificationsservice.service;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PushService {

    private static final Logger LOGGER = Logger.getLogger(PushService.class.getName());

    public boolean sendPushNotification(String userId, String title, String message) {
        try {
            // Simulate push notification sending delay
            Thread.sleep(70);
            LOGGER.info("===== SIMULATION PUSH NOTIFICATION =====");
            LOGGER.info("User ID: " + userId);
            LOGGER.info("Title: " + title);
            LOGGER.info("Message: " + message);
            LOGGER.info("Push notification sent successfully!");
            LOGGER.info("=====================================");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public boolean sendPushNotificationV2(String userId, String title, String message) {
        try {
            // Simulate push notification sending delay for v2 with longer processing time
            Thread.sleep(100);
            LOGGER.info("===== SIMULATION PUSH NOTIFICATION V2 =====");
            LOGGER.info("User ID: " + userId);
            LOGGER.info("Title: " + title);
            LOGGER.info("Message length: " + message.length() + " characters");
            LOGGER.info("Extended push notification sent successfully!");
            LOGGER.info("=========================================");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
