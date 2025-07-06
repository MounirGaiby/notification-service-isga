package com.mounirgaiby.notificationsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Push notification request")
public class PushRequest {

    @Schema(description = "User ID for push notification", example = "user123")
    @NotBlank(message = "User ID is required")
    @Size(max = 100, message = "User ID cannot exceed 100 characters")
    private String userId;

    @Schema(description = "Push notification title", example = "New Message")
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Schema(description = "Push notification message", example = "You have a new message")
    @NotBlank(message = "Message is required")
    @Size(max = 500, message = "Message cannot exceed 500 characters for v1")
    private String message;

    // Constructors
    public PushRequest() {}

    public PushRequest(String userId, String title, String message) {
        this.userId = userId;
        this.title = title;
        this.message = message;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
