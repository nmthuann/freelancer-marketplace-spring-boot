package com.nmt.freelancermarketplacespringboot.common.enums;

import lombok.Getter;

@Getter
public enum ApiVersionEnum {
    API_VERSION_V1("2022-11-28"),
    API_VERSION_V2("2024-05-18"),
    ;
    private final String timestamp;
    ApiVersionEnum(String timestamp){
        this.timestamp = timestamp;
    }
}
