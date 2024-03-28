package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import java.util.Date;

public record UpdatePackageDto (
//        int packageId,
        String packageName,
        String caption,
        String revision,
        Integer deliveryDay,
        Double unitPrice,
        Date beginAt
){
}
