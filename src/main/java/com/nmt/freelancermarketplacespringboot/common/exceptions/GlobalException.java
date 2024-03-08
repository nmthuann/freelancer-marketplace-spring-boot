package com.nmt.freelancermarketplacespringboot.common.exceptions;


import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.InvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalException  { //extends ResponseEntityExceptionHandler
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> authModuleException(@NonNull Exception ex, WebRequest req){
        ErrorDetail errorDetail = new ErrorDetail(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "",
                LocalDateTime.now()
        );

        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
    }

    // something the other method
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBindException(@NonNull MethodArgumentNotValidException ex, WebRequest req) {

//        ErrorDetail errorDetail = new ErrorDetail(
//                HttpStatus.BAD_REQUEST.value(),
//                Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(),
//                "",
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);

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
        ErrorDetail errorDetail = new ErrorDetail(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "",
                LocalDateTime.now()
        );

        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);

//        return handleExceptionInternal(
//                ex,
//                errorDetail,
//                null,
//                HttpStatus.BAD_REQUEST,
//                req);
   }

}





////    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        BindingResult result = ex.getBindingResult();
//        List<FieldError> fieldError = result.getFieldErrors();
//        List<String> errors = new ArrayList<>();
//        for(FieldError error : fieldError) {
//            errors.add(error.getDefaultMessage());
//        }
//
//        ErrorDetail errorResponse = new ErrorDetail(
//                400,
//                errors.toString(),
//                "Bad request",
//                LocalDateTime.now()
//        );
//        return handleExceptionInternal(ex, errorResponse, headers, status, request);
//    }





//@ControllerAdvice
//public class ValidationHandler extends ResponseEntityExceptionHandler{
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) ->{
//
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName, message);
//        });
//        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//}