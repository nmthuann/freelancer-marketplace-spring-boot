package com.nmt.freelancermarketplacespringboot.dto.posts.major;

import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileAttributeDto;

import java.util.Set;

public record CreateMajorDto (
        Integer categoryId,
        String majorName,
        Set<CreateMajorAttributeDto> majorAttributes

) {
}
