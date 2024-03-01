package com.nmt.freelancermarketplacespringboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginDto {
    @Email
    private String email;

    @NotEmpty
    private String password;
}
