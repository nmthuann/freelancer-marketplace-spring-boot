package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.users;

import lombok.Getter;

@Getter
public enum UserExceptionMessages {
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND."),
    CREATE_USER_PAYMENT_FAIL("CREATE_USER_PAYMENT_FAIL."),
    USER_NOT_FOUND_OR_EMAIL_INVALID("Not Found Email or Email Invalid")
    ;


    private final String message;

    UserExceptionMessages(String message){
        this.message = message;
    }
}
