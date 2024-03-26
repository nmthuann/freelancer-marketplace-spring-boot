package com.nmt.freelancermarketplacespringboot.dto.posts.post;

import java.util.List;
import java.util.UUID;

public record CreatePackageDto (
        List<PackageDto> packages
){
}


