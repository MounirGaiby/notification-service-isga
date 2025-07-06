package com.mounirgaiby.notificationsservice.controller;

import com.mounirgaiby.notificationsservice.dto.EmailRequest;
import com.mounirgaiby.notificationsservice.dto.NotificationResponse;
import com.mounirgaiby.notificationsservice.dto.PushRequest;
import com.mounirgaiby.notificationsservice.dto.SmsRequest;
import com.mounirgaiby.notificationsservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notifications API v1 (DEPRECATED)", description = "Simulated notifications API - all notifications are simulated")
@Deprecated
public class NotificationControllerV1 {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/email")
    @Operation(summary = "Send email notification", description = "Send email notification (SIMULATED - v1 is deprecated)")
    public CompletableFuture<ResponseEntity<NotificationResponse>> sendEmail(@Valid @RequestBody EmailRequest emailRequest) {
        return notificationService.sendEmailNotificationV1(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText()
        ).thenApply(success -> {
            NotificationResponse response = new NotificationResponse(
                    success,
                    success ? "Email sent successfully (SIMULATED)" : "Failed to send email",
                    "v1",
                    "email"
            );
            return ResponseEntity.ok(response);
        });
    }

    @PostMapping("/sms")
    @Operation(summary = "Send SMS notification", description = "Send SMS notification with 160 character limit (SIMULATED - v1 is deprecated)")
    public CompletableFuture<ResponseEntity<NotificationResponse>> sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        return notificationService.sendSmsNotificationV1(
                smsRequest.getPhoneNumber(),
                smsRequest.getMessage()
        ).thenApply(success -> {
            NotificationResponse response = new NotificationResponse(
                    success,
                    success ? "SMS sent successfully (SIMULATED)" : "Failed to send SMS",
                    "v1",
                    "sms"
            );
            return ResponseEntity.ok(response);
        });
    }

    @PostMapping("/push")
    @Operation(summary = "Send push notification", description = "Send push notification with 500 character limit (SIMULATED - v1 is deprecated)")
    public CompletableFuture<ResponseEntity<NotificationResponse>> sendPush(@Valid @RequestBody PushRequest pushRequest) {
        return notificationService.sendPushNotificationV1(
                pushRequest.getUserId(),
                pushRequest.getTitle(),
                pushRequest.getMessage()
        ).thenApply(success -> {
            NotificationResponse response = new NotificationResponse(
                    success,
                    success ? "Push notification sent successfully (SIMULATED)" : "Failed to send push notification",
                    "v1",
                    "push"
            );
            return ResponseEntity.ok(response);
        });
    }
}
