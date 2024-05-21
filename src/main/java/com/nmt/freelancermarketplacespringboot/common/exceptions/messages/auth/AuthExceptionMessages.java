package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.auth;

import lombok.Getter;

@Getter
public enum AuthExceptionMessages {
    AUTH_MISSING_INFORMATION("Missing authorization information."),
    AUTH_ERROR("An error occurred while processing authentication."),
    AUTH_MISSING_API_VERSION("Missing api version information."),
    AUTH_INVALID_API_VERSION("Api version information Invalid.")
    ;


    private final String message;

    AuthExceptionMessages(String message){
        this.message = message;
    }

}
