package com.nmt.freelancermarketplacespringboot.services.posts.post;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.CreatePostDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;

import java.util.UUID;

public interface IPostService extends IBaseService<PostEntity, UUID> {

    PostEntity createOne(String email, CreatePostDto data);
}
