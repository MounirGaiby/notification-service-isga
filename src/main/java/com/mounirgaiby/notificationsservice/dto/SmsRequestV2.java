package com.mounirgaiby.notificationsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "SMS notification request for v2 API with extended message length")
public class SmsRequestV2 {

    @Schema(description = "Phone number", example = "+1234567890")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Schema(description = "SMS message text (up to 1600 characters for v2)", example = "Your extended notification message...")
    @NotBlank(message = "Message is required")
    @Size(max = 1600, message = "Message cannot exceed 1600 characters for v2")
    private String message;

    // Constructors
    public SmsRequestV2() {}

    public SmsRequestV2(String phoneNumber, String message) {
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
