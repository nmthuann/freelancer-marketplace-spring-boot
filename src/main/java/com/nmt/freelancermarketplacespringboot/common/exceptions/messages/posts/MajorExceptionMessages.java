package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts;

import lombok.Getter;

@Getter
public enum MajorExceptionMessages {
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND for creation Major ."),
    CREATE_MAJOR_FAIL("CREATE_MAJOR_FAIL.")
    ;


    private final String message;

    MajorExceptionMessages(String message){
        this.message = message;
    }
}
