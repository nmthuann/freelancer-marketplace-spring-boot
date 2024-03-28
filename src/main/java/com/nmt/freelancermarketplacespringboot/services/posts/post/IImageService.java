package com.nmt.freelancermarketplacespringboot.services.posts.post;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.posts.ImageException;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.ImageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.InsertImagesDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;

import java.util.List;
import java.util.UUID;

public interface IImageService extends IBaseService<ImageEntity, UUID> {

    List<ImageEntity> insertImages (PostEntity postCreated, List<ImageDto> data) throws ImageException; //InsertImagesDto data
    List<ImageEntity> updateImages (PostEntity postUpdated, List<ImageDto> data) throws ImageException;

}
