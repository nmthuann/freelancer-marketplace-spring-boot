package com.nmt.freelancermarketplacespringboot.dto.users.profile;


import java.util.Set;

public record CreateProfileDto(
        String occupation,
        Set<CreateProfileAttributeDto> profileAttributes
) {
}


//private String level;
//private String occupation;
//private List<ProfileValueDto> profileAttributeValues;
//private UserDto user;
