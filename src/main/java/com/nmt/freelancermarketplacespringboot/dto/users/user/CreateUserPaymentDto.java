package com.nmt.freelancermarketplacespringboot.dto.users.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CreateUserPaymentDto(
        @Size(min=15, max = 15, message = "enough 15 character")
        @NotEmpty(message = "cardNumber is required")
        String cardNumber,
        @Size(min=20, max = 50, message = "enough 15 character")
        @NotEmpty(message = "cardholderName is required")
        String cardholderName,
        @NotEmpty(message = "expired is required")
        Date expired,
        @Size(min=3, max = 3, message = "enough 3 character")
        @NotEmpty(message = "cvv is required")
        String cvv,
        @NotEmpty(message = "country is required")
        String country
){
}
