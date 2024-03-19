package com.nmt.freelancermarketplacespringboot.common.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class RestError {
    private int statusCode;
    private String message;
    private String description;
    private LocalDateTime timestamp;

    public RestError(int statusCode, String message, String description, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
        this.timestamp = timestamp;
    }
}
