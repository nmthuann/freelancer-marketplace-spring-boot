package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts;

import lombok.Getter;

@Getter
public enum ImageExceptionMessages {
    INSERT_IMAGES_FAIL("INSERT_IMAGES_FAIL."),
    ;


    private final String message;

    ImageExceptionMessages(String message){
        this.message = message;
    }
}
