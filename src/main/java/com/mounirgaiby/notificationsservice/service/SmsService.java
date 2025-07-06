package com.mounirgaiby.notificationsservice.service;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SmsService {

    private static final Logger LOGGER = Logger.getLogger(SmsService.class.getName());

    public boolean sendSms(String phoneNumber, String message) {
        try {
            // Simulate SMS sending delay
            Thread.sleep(50);
            LOGGER.info("SIMULATED: SMS sent to: " + phoneNumber + " with message: " + message);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public boolean sendSmsV2(String phoneNumber, String message) {
        try {
            // Simulate SMS sending delay for v2 with longer processing time
            Thread.sleep(80);
            LOGGER.info("SIMULATED V2: Extended SMS sent to: " + phoneNumber + " with message length: " + message.length());
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
