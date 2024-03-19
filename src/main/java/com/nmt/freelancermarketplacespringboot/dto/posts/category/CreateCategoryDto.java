package com.nmt.freelancermarketplacespringboot.dto.posts.category;

public record CreateCategoryDto(
        Integer categoryParentId,
        String categoryName // need add
) {
}
