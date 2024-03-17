package com.nmt.freelancermarketplacespringboot.dto.users.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record UserDto (
        @NotEmpty String firstName,
        @NotEmpty String lastName,
        @NotEmpty String gender,
        Date birthday,
        @NotEmpty String phone,
        @NotEmpty String location,
        String avatarURL
){
}
