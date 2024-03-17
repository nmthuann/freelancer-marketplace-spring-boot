package com.nmt.freelancermarketplacespringboot.common.exceptions.errors;

public class UserException extends Exception{
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
