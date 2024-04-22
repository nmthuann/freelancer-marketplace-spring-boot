package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;

import java.util.List;
import java.util.UUID;

public record GetPostDto(
        int majorId,
        UUID postId,
        String title,
        String description,
        String faq,
        String sellerEmail,
        String sellerFullName,
        List<ImageDto> images,
        List<PackageDto>packages
) {
}
