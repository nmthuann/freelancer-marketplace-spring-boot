package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreatePostDto (
        @NotEmpty(message = "title is require!")
        String title,
        @NotEmpty(message = "description is require!")
        String description,
        String faq,
        @NotNull(message = "majorId is require!")
        int majorId,
        @NotEmpty(message = "images is require!")
        // String userId, -> get into Token
        List<ImageDto> images,
        @NotEmpty(message = "packages is require!")
        List<PackageDto>packages
) {
}

