package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import jakarta.persistence.Column;

import java.util.List;

public record CreatePostDto (
        String title,
        String description,
        String faq,
        int majorId,
        // String userId, -> get into Token
        List<ImageDto> images,
        List<PackageDto>packages
) {
}

