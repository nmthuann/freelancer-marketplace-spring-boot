package com.nmt.freelancermarketplacespringboot.dto.auth;

import java.time.LocalDateTime;
import java.util.Date;

public record ResultLogoutDto(
        String email,
        int status,
        String message,
        LocalDateTime timestamp
) {
}
