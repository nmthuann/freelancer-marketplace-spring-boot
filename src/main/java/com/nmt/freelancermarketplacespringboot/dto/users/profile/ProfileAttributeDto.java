package com.nmt.freelancermarketplacespringboot.dto.users.profile;

import java.util.Set;

public record ProfileAttributeDto (
        Integer profileAttributeId,
        String profileAttributeName,
        Set<ProfileValueDto> profileValues
) {
}
