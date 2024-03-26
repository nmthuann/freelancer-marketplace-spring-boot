package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import java.util.Set;
import java.util.UUID;

public record InsertImagesDto(
        UUID postId,
        Set<ImageDto> images
) {
}
