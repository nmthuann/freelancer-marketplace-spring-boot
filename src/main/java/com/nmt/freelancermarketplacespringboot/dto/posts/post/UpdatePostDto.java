package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import java.util.List;

public record UpdatePostDto (
        String title,
        String description,
        String faq,
        // String userId, -> get into Token
        List<ImageDto> images,
        List<PackageDto>packages
) {
}
