package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import java.util.Date;

public record PackageDto(
        String packageName,
        String caption,
        String revision,
        Integer deliveryDay,
        Double unitPrice,
        Date beginAt
) {
}


//@Column(name = "package_name", length = 50, nullable = false)
//private String packageName;
//
//@Column(name = "caption")
//private String caption;
//
//@Column(name = "revision", length = 255)
//private String revision;
//
//@Column(name = "delivery_day", nullable = false)
//private Integer deliveryDay;


