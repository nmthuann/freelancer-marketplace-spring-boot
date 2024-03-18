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

    public RestError(int value, String authenticationFailedAtControllerAdvice, String s, LocalDateTime now) {
    }
}
