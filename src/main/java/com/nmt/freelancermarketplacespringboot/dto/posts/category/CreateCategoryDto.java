package com.nmt.freelancermarketplacespringboot.dto.posts.category;

import jakarta.validation.constraints.NotEmpty;

public record CreateCategoryDto(
        @NotEmpty(message = "title is require!")
        Integer parentId,
        @NotEmpty(message = "title is require!")
        String categoryName, // need add

        String description
) {
}
