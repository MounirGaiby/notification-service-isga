package com.mounirgaiby.notificationsservice.controller;

import com.mounirgaiby.notificationsservice.dto.EmailRequest;
import com.mounirgaiby.notificationsservice.dto.NotificationResponse;
import com.mounirgaiby.notificationsservice.dto.PushRequestV2;
import com.mounirgaiby.notificationsservice.dto.SmsRequestV2;
import com.mounirgaiby.notificationsservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v2/notifications")
@Tag(name = "Notifications API v2", description = "Enhanced notifications API - Email is real, SMS and Push support extended messages")
public class NotificationControllerV2 {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/email")
    @Operation(summary = "Send email notification", description = "Send real email notification using configured SMTP server")
    public CompletableFuture<ResponseEntity<NotificationResponse>> sendEmail(@Valid @RequestBody EmailRequest emailRequest) {
        return notificationService.sendEmailNotificationV2(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText()
        ).thenApply(success -> {
            NotificationResponse response = new NotificationResponse(
                    success,
                    success ? "Email sent successfully (REAL)" : "Failed to send email",
                    "v2",
                    "email"
            );
            return ResponseEntity.ok(response);
        });
    }

    @PostMapping("/sms")
    @Operation(summary = "Send SMS notification", description = "Send SMS notification with extended message length up to 1600 characters (SIMULATED)")
    public CompletableFuture<ResponseEntity<NotificationResponse>> sendSms(@Valid @RequestBody SmsRequestV2 smsRequest) {
        return notificationService.sendSmsNotificationV2(
                smsRequest.getPhoneNumber(),
                smsRequest.getMessage()
        ).thenApply(success -> {
            NotificationResponse response = new NotificationResponse(
                    success,
                    success ? "Extended SMS sent successfully (SIMULATED)" : "Failed to send SMS",
                    "v2",
                    "sms"
            );
            return ResponseEntity.ok(response);
        });
    }

    @PostMapping("/push")
    @Operation(summary = "Send push notification", description = "Send push notification with extended message length up to 2000 characters (SIMULATED)")
    public CompletableFuture<ResponseEntity<NotificationResponse>> sendPush(@Valid @RequestBody PushRequestV2 pushRequest) {
        return notificationService.sendPushNotificationV2(
                pushRequest.getUserId(),
                pushRequest.getTitle(),
                pushRequest.getMessage()
        ).thenApply(success -> {
            NotificationResponse response = new NotificationResponse(
                    success,
                    success ? "Extended push notification sent successfully (SIMULATED)" : "Failed to send push notification",
                    "v2",
                    "push"
            );
            return ResponseEntity.ok(response);
        });
    }
}
