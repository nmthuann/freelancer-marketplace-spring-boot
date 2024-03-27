package com.nmt.freelancermarketplacespringboot.services.posts.post;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.CreatePostDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PostDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.UpdatePostDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;

import java.util.List;
import java.util.UUID;

public interface IPostService extends IBaseService<PostEntity, UUID> {

    PostDto createOne(String email, CreatePostDto data);
    PostEntity updateOneById(UUID postId, UpdatePostDto data);
    List<PostEntity> getPostsByUserId(String email);
    List<PostEntity> getPostsByMajorId(int majorId);
    List<PostEntity> getPostsByCategoryId(int categoryId);
}
