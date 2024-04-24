package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;

import java.util.List;
import java.util.UUID;

public record PostDto(
        int majorId,
        UUID postId,
        String title,
        String description,
        String faq,
        Float rating,
        Integer vote,
        String sellerEmail,
        String sellerFullName,
        // String userId, -> get into Token
//        List<ImageDto> images,
//        List<PackageDto>packages
        List<ImageEntity> images,
        List<PackageEntity>packages
) {
}
//List<ImageEntity> images,
//List<PackageEntity>packages
