package com.nmt.freelancermarketplacespringboot.dto.posts.post;

public record PackageDto(
        String packageName,
        String caption,
        String revision,
        Integer deliveryDay
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


