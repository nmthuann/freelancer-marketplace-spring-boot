package com.nmt.freelancermarketplacespringboot.services.posts.post;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.CreatePostDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PostDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.UpdatePackageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.UpdatePostDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IPostService extends IBaseService<PostEntity, UUID> {

    PostDto createOne(String email, CreatePostDto data) throws ModuleException;
    PostEntity updateOneById(String email, UUID postId, UpdatePostDto data) throws ModuleException, ImageException;
    List<PostEntity> getPostsByUserId(String email);
    Page<PostEntity> getPostsByMajorId(int majorId, int size, int page);
    List<PostEntity> getPostsByCategoryId(int categoryId);
    void softDelete(UUID postId);
    PackageEntity updatePackageByPost(UUID postId, int packageId, UpdatePackageDto data) throws ModuleException;
    Page<PostEntity> getAllPosts(
            int size,
            int page,
            int majorId,
            Boolean latest,
            Boolean bestSeller,
            Boolean topFeedback
    );


}
