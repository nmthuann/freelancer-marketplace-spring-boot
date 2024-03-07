package com.nmt.freelancermarketplacespringboot.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<DetailError> myIllegalHandler(CustomException ie, WebRequest req){
        DetailError err = new DetailError (
                LocalDateTime.now(),
                ie.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<DetailError> mynotFoundHandler(NoHandlerFoundException nfe, WebRequest req) {
        DetailError err=new DetailError(
                LocalDateTime.now(),
                nfe.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
