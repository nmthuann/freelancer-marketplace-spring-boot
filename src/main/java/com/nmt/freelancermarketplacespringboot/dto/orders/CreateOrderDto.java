package com.nmt.freelancermarketplacespringboot.dto.orders;

import com.nmt.freelancermarketplacespringboot.dto.posts.post.ImageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PackageDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderDto (
        @NotEmpty(message = "title is require!")
        String title,
        @NotEmpty(message = "description is require!")
        String description
){
}
