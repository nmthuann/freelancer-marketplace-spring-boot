package com.nmt.freelancermarketplacespringboot.dto.users.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record CreateUserPaymentDto (
        // missing field card type
        @NotEmpty(message = "cardNumber is required")
        @Size(min=16, max = 16, message = "Card number must be exactly 16 characters")
        String cardNumber,
        @NotEmpty(message = "cardholderName is required")
        @Size(min=5, max = 50, message = "Cardholder name must be between 20 and 50 character")
        String cardholderName,
        @NotNull(message = "expired is required")
        //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        Date expired,
        @Size(min=3, max = 3, message = "enough 3 character")
        @NotEmpty(message = "cvv is required")
        String cvv,
        @NotEmpty(message = "country is required")
        String country
){
}
