package com.nmt.freelancermarketplacespringboot.dto.posts.major;

import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileValueDto;

import java.util.Set;

public record CreateMajorAttributeDto (
        Integer majorAttributeId,
        Set<CreateMajorValueDto> majorValues
) {
}
