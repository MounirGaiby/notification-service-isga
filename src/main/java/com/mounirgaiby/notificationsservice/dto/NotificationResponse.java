package com.mounirgaiby.notificationsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Notification response")
public class NotificationResponse {

    @Schema(description = "Operation success status", example = "true")
    private boolean success;

    @Schema(description = "Response message", example = "Email sent successfully")
    private String message;

    @Schema(description = "API version used", example = "v1")
    private String version;

    @Schema(description = "Notification type", example = "email")
    private String type;

    // Constructors
    public NotificationResponse() {}

    public NotificationResponse(boolean success, String message, String version, String type) {
        this.success = success;
        this.message = message;
        this.version = version;
        this.type = type;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
