package com.nmt.freelancermarketplacespringboot.dto.posts.major;

import java.util.Set;

public record CreateMajorEavDto(
        Integer categoryId,
        String majorName,
        Set<CreateMajorAttributeDto> majorAttributes

) {
}
