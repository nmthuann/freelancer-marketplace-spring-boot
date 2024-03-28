package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts;

import lombok.Getter;

@Getter
public enum ImageExceptionMessage {
    INSERT_IMAGES_FAIL("INSERT_IMAGES_FAIL."),
    ;


    private final String message;

    ImageExceptionMessage(String message){
        this.message = message;
    }
}
