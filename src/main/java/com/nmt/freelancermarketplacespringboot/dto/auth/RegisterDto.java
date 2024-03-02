package com.nmt.freelancermarketplacespringboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record RegisterDto (
        @Email @NotEmpty String email,
        @NotEmpty String password,
        @NotEmpty String firstName,
        @NotEmpty String lastName,
        @NotEmpty String gender,
        @NotEmpty Date birthday,
        @NotEmpty String phone
){}
