package com.mounirgaiby.notificationsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "SMS notification request")
public class SmsRequest {

    @Schema(description = "Phone number", example = "+1234567890")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Schema(description = "SMS message text", example = "Your verification code is: 123456")
    @NotBlank(message = "Message is required")
    @Size(max = 160, message = "Message cannot exceed 160 characters for v1")
    private String message;

    // Constructors
    public SmsRequest() {}

    public SmsRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    // Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
