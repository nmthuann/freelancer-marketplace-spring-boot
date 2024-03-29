package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.users;

import lombok.Getter;

@Getter
public enum UserExceptionMessages {
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND."),
    CREATE_USER_PAYMENT_FAIL("CREATE_USER_PAYMENT_FAIL."),
    ;


    private final String message;

    UserExceptionMessages(String message){
        this.message = message;
    }
}
