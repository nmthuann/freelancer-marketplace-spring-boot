package com.nmt.freelancermarketplacespringboot.dto.orders;

import java.util.UUID;

public record CreateTransactionDto(
        UUID orderId,
        Double amount,
        int paymentMethodId,
        String description
        // UUID userId
        // String status
) {
}
