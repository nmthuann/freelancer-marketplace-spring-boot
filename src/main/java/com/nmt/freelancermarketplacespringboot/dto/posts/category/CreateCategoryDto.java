package com.nmt.freelancermarketplacespringboot.dto.posts.category;

public record CreateCategoryDto(
        Integer parentId,
        String categoryName, // need add
        String description
) {
}
