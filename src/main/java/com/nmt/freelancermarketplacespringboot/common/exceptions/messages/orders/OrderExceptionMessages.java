package com.nmt.freelancermarketplacespringboot.common.exceptions.messages.orders;


import lombok.Getter;

@Getter
public enum OrderExceptionMessages {
    ORDER_TRANSACTION_NOT_CREATED("ORDER_TRANSACTION_NOT_CREATED, so Order cancelled ."),
    ;


    private final String message;
    OrderExceptionMessages(String message){
        this.message = message;
    }
}
