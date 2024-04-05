package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts;

import lombok.Getter;

@Getter
public enum PostExceptionMessages {
    CREATE_POST_FAIL("CREATE_POST_FAIL."),
    UPDATE_POST_FAIL("UPDATE_POST_FAIL."),
    NOT_THE_OWNER_POST("NOT_THE_OWNER_POST."),
    PACKAGE_NOT_FOUND ("PACKAGE_NOT_FOUND."),
    USER_NOT_PROFILE("User don't have profile"),
    MAJOR_INVALID("Major Id Invalid.")
    ;


    private final String message;

    PostExceptionMessages(String message){
        this.message = message;
    }
}
