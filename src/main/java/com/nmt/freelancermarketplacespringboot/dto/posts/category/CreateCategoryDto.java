package com.nmt.freelancermarketplacespringboot.dto.posts.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCategoryDto(
        @NotNull(message = "parentId is require!")
        int parentId,
        @NotEmpty(message = "categoryName is require!")
        String categoryName, // need add
        String description
) {
}
