package com.nmt.freelancermarketplacespringboot.common.exceptions;

import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler({ AuthenticationException.class })
//    @ResponseBody
//    public ResponseEntity<RestError> handleAuthenticationException(Exception ex) {
//
//        RestError re = new RestError(
//                HttpStatus.UNAUTHORIZED.value(),
//                ex.getMessage(),
//                "Spring security threw error!",
//                LocalDateTime.now());
//        // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
//        return new ResponseEntity<>(re, HttpStatus.UNAUTHORIZED);
//    }
@ExceptionHandler({ AuthenticationException.class, ServletException.class }) // Include ServletException in the exception handler
@ResponseBody
public ResponseEntity<RestError> handleAuthenticationException(Exception ex) {
    int statusCode;
    String message;

    if (ex instanceof AuthenticationException) {
        statusCode = HttpStatus.UNAUTHORIZED.value();
        message = ex.getMessage();
    } else if (ex instanceof ServletException) {
        statusCode = HttpStatus.UNAUTHORIZED.value();
        message = "Missing authorization information"; // Customize the message
    } else {
        // Handle other exceptions
        statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        message = "Internal server error";
    }

    RestError re = new RestError(
            statusCode,
            message,
            "Spring security threw error!",
            LocalDateTime.now());

    return new ResponseEntity<>(re, HttpStatus.valueOf(statusCode));
}
}
