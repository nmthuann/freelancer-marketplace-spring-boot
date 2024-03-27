package com.nmt.freelancermarketplacespringboot.common.enums;

import lombok.Getter;

@Getter
public enum PostStatusEnum {
    POST_ACTIVE("ACTIVE"),
    POST_UPDATED("UPDATED"),
    POST_STOPPED("STOPPED");
    private final String status;
    PostStatusEnum(String status) {
        this.status = status;
    }
}
