package com.nmt.freelancermarketplacespringboot.common.enums;

import lombok.Getter;

@Getter
public enum ProfileLevelEnum {
    NEW_SELLER("NEW_SELLER"),
    PROFESSIONAL("PROFESSIONAL"),
    // POST_STOPPED("STOPPED")
    ;
    private final String level;
    ProfileLevelEnum(String level) {
        this.level = level;
    }
}
