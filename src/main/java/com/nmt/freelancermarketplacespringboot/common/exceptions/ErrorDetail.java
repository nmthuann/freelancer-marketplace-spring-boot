package com.nmt.freelancermarketplacespringboot.common.exceptions;

import java.time.LocalDateTime;

public class ErrorDetail {
    private int statusCode;
    private String message;
    private String description;
    private LocalDateTime timestamp;

    public ErrorDetail(int statusCode, String message, String description, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MyErrorDetails{" +
                "status Code=" + statusCode +
                ", timestamp='" + timestamp + '\'' +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
