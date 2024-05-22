package com.nmt.freelancermarketplacespringboot.common.enums;

import lombok.Getter;

@Getter
public enum TransactionStatusEnum {
    TRANSACTION_CREATED("CREATED"),
    TRANSACTION_SUCCEEDED("SUCCEEDED"),
    TRANSACTION_CANCELED("CANCELED"),
    TRANSACTION_FAILED("FAILED");
    private final String status;
    TransactionStatusEnum(String status) {
        this.status = status;
    }
}
