package com.nmt.freelancermarketplacespringboot.common.exceptions.errors;

//@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidException extends Exception {


    public InvalidException() {
    }
    public InvalidException(String message) {
        super(message);
    }
}
