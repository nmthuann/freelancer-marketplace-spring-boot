package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.auth;

import lombok.Getter;

@Getter
public enum AuthExceptionMessages {
    AUTH_MISSING_INFORMATION("Missing authorization information."),
    AUTH_ERROR("An error occurred while processing authentication."),
    ;


    private final String message;

    AuthExceptionMessages(String message){
        this.message = message;
    }

}
