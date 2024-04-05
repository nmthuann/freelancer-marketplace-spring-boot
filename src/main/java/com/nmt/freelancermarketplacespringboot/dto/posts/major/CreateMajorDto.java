package com.nmt.freelancermarketplacespringboot.dto.posts.major;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public record CreateMajorDto(
        @NonNull
        int categoryId,
        @NotEmpty
        String majorName
) {
}
