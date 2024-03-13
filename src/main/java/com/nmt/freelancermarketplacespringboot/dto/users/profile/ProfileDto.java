package com.nmt.freelancermarketplacespringboot.dto.users.profile;

import lombok.Data;

import java.util.Set;


public record ProfileDto (
        String email,
        String firstName,
        String lastName,

        String level,
        String occupation,
        Set<ProfileAttributeDto> profileAttributes
) {
//    public ProfileDto(
//            String email, String lastName, String firstName, String level, String occupation,
//                      Set<ProfileAttributeDto> profileAttributes) {
//        this(email, lastName, firstName, level, occupation, profileAttributes);
//    }
}
