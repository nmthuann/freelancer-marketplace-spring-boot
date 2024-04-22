package com.nmt.freelancermarketplacespringboot.common.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    ORDER_PENDING("PENDING"),
    ORDER_CONFIRMED("CONFIRMED("),
    ORDER_IN_PROGRESS("IN_PROGRESS"),
    ORDER_CANCELED("CANCELED"),
    ORDER_COMPLETED("COMPLETED");
    private final String status;
    OrderStatusEnum(String status) {
        this.status = status;
    }
}
