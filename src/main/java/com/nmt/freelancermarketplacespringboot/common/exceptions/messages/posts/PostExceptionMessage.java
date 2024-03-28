package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts;

import lombok.Getter;

@Getter
public enum PostExceptionMessage {
    CREATE_POST_FAIL("CREATE_POST_FAIL."),
    UPDATE_POST_FAIL("UPDATE_POST_FAIL."),
    NOT_THE_OWNER_POST("NOT_THE_OWNER_POST."),
    PACKAGE_NOT_FOUND ("PACKAGE_NOT_FOUND.")
    ;


    private final String message;

    PostExceptionMessage(String message){
        this.message = message;
    }
}
