package com.nmt.freelancermarketplacespringboot.common.exceptions.messages;

import lombok.Getter;

@Getter
public enum UserExceptionMessage {
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND."),
    CREATE_USER_PAYMENT_FAIL("CREATE_USER_PAYMENT_FAIL."),
    ;


    private final String message;

    UserExceptionMessage(String message){
        this.message = message;
    }
}
