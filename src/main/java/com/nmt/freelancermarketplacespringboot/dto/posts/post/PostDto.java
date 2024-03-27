package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;

import java.util.List;
import java.util.UUID;

public record PostDto(
        UUID postId,
        String title,
        String description,
        String faq,
        int majorId,
        // String userId, -> get into Token
        List<ImageEntity> images,
        List<PackageEntity>packages
) {
}
