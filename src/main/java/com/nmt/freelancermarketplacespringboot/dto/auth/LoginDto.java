package com.nmt.freelancermarketplacespringboot.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/***
 * ☺ ☺ ☺
 */
public record LoginDto(@Email String email, @NotEmpty String password) {}


//private String email;
//
//@NotEmpty
//private String password;