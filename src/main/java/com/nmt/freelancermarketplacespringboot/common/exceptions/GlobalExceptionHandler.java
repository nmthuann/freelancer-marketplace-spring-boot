package com.nmt.freelancermarketplacespringboot.common.exceptions;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.InvalidException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(
            HttpServletRequest request, AuthenticationException ex) {
        HttpStatus statusCode = HttpStatus.UNAUTHORIZED;
        RestError re = new RestError(
                statusCode.value(),
                ex.getMessage(),
                request.getAttribute("message").toString(),
                LocalDateTime.now());
        return new ResponseEntity<>(re, statusCode);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public ResponseEntity<RestError> handleAccessDeniedException(IllegalStateException ex) {
        HttpStatus statusCode = HttpStatus.FORBIDDEN;
        RestError re = new RestError(
                statusCode.value(),
                "IllegalStateException: " + ex.getMessage(),
                "Spring Security Access Denied Error",
                LocalDateTime.now());
        return new ResponseEntity<>(re, statusCode);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<RestError> handleException(Exception ex) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        RestError re = new RestError(
                statusCode.value(),
                "Internal Server Error",
                "Spring Security Internal Server Error",
                LocalDateTime.now());
        return new ResponseEntity<>(re, statusCode);
    }


    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> authModuleException(@NonNull Exception ex, WebRequest req){
        RestError errorDetail = new RestError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "",
                LocalDateTime.now()
        );
        return new ResponseEntity<RestError>(errorDetail, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBindException(@NonNull MethodArgumentNotValidException ex, WebRequest req) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> invalidException(@NonNull Exception ex, WebRequest req){
        RestError errorDetail = new RestError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "",
                LocalDateTime.now()
        );
        return new ResponseEntity<RestError>(errorDetail, HttpStatus.NOT_FOUND);
    }


}

