package com.nmt.freelancermarketplacespringboot.services.posts.post;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.CreatePackageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PackageDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;

import java.util.List;

public interface IPackageService extends IBaseService<PackageEntity, Integer> {

    List<PackageEntity> createPackageByPost(PostEntity postCreated, List<PackageDto> data);
}
