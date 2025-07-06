package com.mounirgaiby.notificationsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Push notification request for v2 API with extended message length")
public class PushRequestV2 {

    @Schema(description = "User ID for push notification", example = "user123")
    @NotBlank(message = "User ID is required")
    @Size(max = 100, message = "User ID cannot exceed 100 characters")
    private String userId;

    @Schema(description = "Push notification title", example = "New Message")
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Schema(description = "Push notification message (up to 2000 characters for v2)", example = "You have a new extended message...")
    @NotBlank(message = "Message is required")
    @Size(max = 2000, message = "Message cannot exceed 2000 characters for v2")
    private String message;

    // Constructors
    public PushRequestV2() {}

    public PushRequestV2(String userId, String title, String message) {
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
