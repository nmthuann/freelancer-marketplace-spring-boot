package com.nmt.freelancermarketplacespringboot.common.httpexceptions;

import com.nmt.freelancermarketplacespringboot.common.errors.exceptions.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(AuthException authException) {
    }
}
