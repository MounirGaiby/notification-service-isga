package com.mounirgaiby.notificationsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Email notification request")
public class EmailRequest {

    @Schema(description = "Recipient email address", example = "user@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String to;

    @Schema(description = "Email subject", example = "Welcome to our service")
    @NotBlank(message = "Subject is required")
    @Size(max = 255, message = "Subject cannot exceed 255 characters")
    private String subject;

    @Schema(description = "Email body content", example = "Hello, welcome to our service!")
    @NotBlank(message = "Text is required")
    private String text;

    // Constructors
    public EmailRequest() {}

    public EmailRequest(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    // Getters and Setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
