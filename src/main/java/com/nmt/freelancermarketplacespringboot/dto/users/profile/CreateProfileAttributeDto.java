package com.nmt.freelancermarketplacespringboot.dto.users.profile;

import java.util.Set;

public record CreateProfileAttributeDto(
        Integer profileAttributeId,
//        String profileAttributeName,
        Set<CreateProfileValueDto> profileValues
) {
}
