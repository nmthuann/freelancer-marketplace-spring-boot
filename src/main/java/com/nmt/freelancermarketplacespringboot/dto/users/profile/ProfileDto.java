package com.nmt.freelancermarketplacespringboot.dto.users.profile;

import lombok.Data;

import java.util.Set;

@Data
public record ProfileDto (
        String email,
        String firstName,
        String lastName,

        String level,
        String occupation,
        Set<ProfileAttributeDto> profileAttributes
) {
}
