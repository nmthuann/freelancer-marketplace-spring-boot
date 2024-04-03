package com.nmt.freelancermarketplacespringboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record RegisterDto (
        @NotEmpty(message = "Email is required")
        @Email
        String email,
        @NotEmpty(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,
        @NotEmpty String firstName,
        @NotEmpty String lastName,
        @NotEmpty String gender,
        Date birthday,
        @NotEmpty String phone,
        @NotEmpty String location
){}

