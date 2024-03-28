package com.nmt.freelancermarketplacespringboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


/***
 * ☺ ☺ ☺
 */

public record LoginDto (
        @NotEmpty(message = "Email is required")
        @Email
        String email,
        @NotEmpty(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 8 characters long")
        String password
) {}


