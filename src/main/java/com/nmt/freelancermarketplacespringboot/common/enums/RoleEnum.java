package com.nmt.freelancermarketplacespringboot.common.enums;

public enum RoleEnum {

    ADMIN(1),
    USER(2),
    CUSTOMER(3),
    MANAGER(5);

    private final int roleId;

    RoleEnum(int roleId){
        this.roleId = roleId;
    }
}