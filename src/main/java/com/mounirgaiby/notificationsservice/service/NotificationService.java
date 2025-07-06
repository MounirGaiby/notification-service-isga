package com.mounirgaiby.notificationsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private SmsService smsService;

    @Autowired
    private PushService pushService;

    public enum NotificationType {
        EMAIL, SMS, PUSH
    }

    // V1 Methods (Deprecated - Simulated)
    public CompletableFuture<Boolean> sendEmailNotificationV1(String to, String subject, String text) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("===== DEMARRAGE EMAIL NOTIFICATION V1 =====");
                LOGGER.info("Destinataire: " + to);
                LOGGER.info("Sujet: " + subject);
                return emailService.sendEmailSimulated(to, subject, text);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to send email notification (V1)", e);
                return false;
            }
        });
    }

    public CompletableFuture<Boolean> sendSmsNotificationV1(String phoneNumber, String message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("===== DEMARRAGE SMS NOTIFICATION V1 =====");
                LOGGER.info("Destinataire: " + phoneNumber);
                return smsService.sendSms(phoneNumber, message);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to send SMS notification (V1)", e);
                return false;
            }
        });
    }

    public CompletableFuture<Boolean> sendPushNotificationV1(String userId, String title, String message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("===== DEMARRAGE PUSH NOTIFICATION V1 =====");
                LOGGER.info("User ID: " + userId);
                LOGGER.info("Title: " + title);
                return pushService.sendPushNotification(userId, title, message);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to send push notification (V1)", e);
                return false;
            }
        });
    }

    // V2 Methods (Enhanced)
    public CompletableFuture<Boolean> sendEmailNotificationV2(String to, String subject, String text) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("===== DEMARRAGE EMAIL NOTIFICATION V2 =====");
                LOGGER.info("Destinataire: " + to);
                LOGGER.info("Sujet: " + subject);

                if (emailService.isEmailConfigured()) {
                    return emailService.sendEmail(to, subject, text);
                } else {
                    LOGGER.info("SMTP non configuré, utilisation de la simulation");
                    return emailService.sendEmailSimulated(to, subject, text);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to send email notification (V2)", e);
                return false;
            }
        });
    }

    public CompletableFuture<Boolean> sendSmsNotificationV2(String phoneNumber, String message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("===== DEMARRAGE SMS NOTIFICATION V2 =====");
                LOGGER.info("Destinataire: " + phoneNumber);
                LOGGER.info("Message length: " + message.length() + " characters");
                return smsService.sendSmsV2(phoneNumber, message);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to send SMS notification (V2)", e);
                return false;
            }
        });
    }

    public CompletableFuture<Boolean> sendPushNotificationV2(String userId, String title, String message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LOGGER.info("===== DEMARRAGE PUSH NOTIFICATION V2 =====");
                LOGGER.info("User ID: " + userId);
                LOGGER.info("Title: " + title);
                LOGGER.info("Message length: " + message.length() + " characters");
                return pushService.sendPushNotificationV2(userId, title, message);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to send push notification (V2)", e);
                return false;
            }
        });
    }
}
