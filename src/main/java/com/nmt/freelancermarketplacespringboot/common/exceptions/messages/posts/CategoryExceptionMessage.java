package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts;

import lombok.Getter;

@Getter
public enum CategoryExceptionMessage {
    CATEGORY_PARENT_NOT_FOUND("CATEGORY_PARENT_NOT_FOUND."),
    ;


    private final String message;

    CategoryExceptionMessage(String message){
        this.message = message;
    }
}
