package com.nmt.freelancermarketplacespringboot.dto.users.profile;

public record CreateProfileValueDto(
        Integer profileAttributeId,
        String profileValue
) {
}
