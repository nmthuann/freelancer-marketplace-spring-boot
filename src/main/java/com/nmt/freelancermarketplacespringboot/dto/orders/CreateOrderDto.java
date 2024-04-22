package com.nmt.freelancermarketplacespringboot.dto.orders;

import com.nmt.freelancermarketplacespringboot.dto.posts.post.ImageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PackageDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record CreateOrderDto (
        int packageId,
        Date deliveryDay,
        Double totalPrice

){
}
