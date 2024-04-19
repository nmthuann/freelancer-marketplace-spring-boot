package com.nmt.freelancermarketplacespringboot.dto.posts.review;

import java.util.UUID;

public record CreateReviewDto(
    int rating,
    String feedback,
    String imageUrl,
    UUID postId
) {
}
