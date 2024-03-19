package com.nmt.freelancermarketplacespringboot.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(Exception ex) {

        RestError re = new RestError(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                "Spring security threw error!",
                LocalDateTime.now());
        // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
        return new ResponseEntity<>(re, HttpStatus.UNAUTHORIZED);
    }
}
