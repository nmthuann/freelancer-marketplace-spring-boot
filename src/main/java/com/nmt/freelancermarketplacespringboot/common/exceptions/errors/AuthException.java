package com.nmt.freelancermarketplacespringboot.common.exceptions.errors;

public class AuthException extends Exception {
    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }
}
