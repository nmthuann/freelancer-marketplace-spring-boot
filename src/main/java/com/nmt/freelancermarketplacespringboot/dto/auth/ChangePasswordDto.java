package com.nmt.freelancermarketplacespringboot.dto.auth;

public record ChangePasswordDto(
        String email,
        String CurrentPassword,
        String NewPassword
) {
}
