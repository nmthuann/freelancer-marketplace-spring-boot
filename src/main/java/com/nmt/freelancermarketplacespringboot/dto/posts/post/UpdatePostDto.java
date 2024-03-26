package com.nmt.freelancermarketplacespringboot.dto.posts.post;

public record UpdatePostDto (
        String title,
        String description,
        String faq
) {
}
