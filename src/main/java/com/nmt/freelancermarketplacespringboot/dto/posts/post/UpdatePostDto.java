package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record UpdatePostDto (
        @NotEmpty(message = "title is require!")
        String title,
        @NotEmpty(message = "description is empty!")
        String description,
        String faq,
        // String userId, -> get into Token
        String status,
        List<ImageDto> images
//        List<PackageDto>packages
) {
}
