package com.nmt.freelancermarketplacespringboot.services.posts.post;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.*;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IPostService extends IBaseService<PostEntity, UUID> {
    PostDto createOne(String email, CreatePostDto data) throws ModuleException;
    PostEntity updateOneById(String email, UUID postId, UpdatePostDto data) throws ModuleException;
    void softDelete(UUID postId);
    PackageEntity updatePackageByPost(UUID postId, int packageId, UpdatePackageDto data) throws ModuleException;

    List<PostEntity> getPostsByUserId(String email);
    Page<GetPostDto> getPostsByMajorId(int majorId,  int page, int size);
    Page<GetPostDto> getPostsByCategoryId(int categoryId, int page, int size);
    Page<GetPostDto> getAllPost(int page, int size, boolean latest);
    GetPostDto getPostById(UUID postId);



    Page<GetPostDto> getAllPosts(
            int size,
            int page,
            int majorId,
            Boolean latest,
            Boolean bestSeller,
            Boolean topFeedback
    );
    PostDto getPostByPackages(UUID postId);

}
