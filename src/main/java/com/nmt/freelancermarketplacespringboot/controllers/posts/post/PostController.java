package com.nmt.freelancermarketplacespringboot.controllers.posts.post;


import com.nmt.freelancermarketplacespringboot.common.enums.PostStatusEnum;
import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.posts.ImageException;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.CreatePostDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PostDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.UpdatePackageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.UpdatePostDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**
 * 1. createPost -> ok
 * 2. updatePost -> ok
 * 3. getOneById -> Ok
 * 4. getAll {size, page, latest, by major, best seller, top feedback}
 * 5. getPostByUser -> OK
 * 6. getPostByMajor -> OK
 * 7. getPostByCategory -> OK
 * 8. updatePackage -> OK
 * 9. updateImages -> OK
 * 10.stopPost -> OK
 * 11.deletePost -> OK
 *
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    IPostService postService;


    public PostController (IPostService postService){
        this.postService = postService;
    }



    @PostMapping("/create")
    public ResponseEntity<?> createOne (
            @Valid @RequestBody CreatePostDto data,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws ImageException {
        System.out.println(data.toString());
        PostDto result = this.postService.createOne(userDetails.getUsername(), data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateOneById (
            @Valid @RequestBody UpdatePostDto data,
            @PathVariable UUID id,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws ImageException, ModuleException {

        PostEntity result = this.postService.updateOneById(userDetails.getUsername(), id, data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PutMapping("/stop{id}")
    public ResponseEntity<?> stopPostById (
            @Valid @RequestBody UpdatePostDto data,
            @PathVariable UUID id,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws ImageException, ModuleException {
        UpdatePostDto updatePostDto = new UpdatePostDto(
                data.title(),
                data.description(),
                data.faq(),
                PostStatusEnum.POST_STOPPED.getStatus(),
                null
        );
        PostEntity result = this.postService.updateOneById(userDetails.getUsername(), id, updatePostDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PutMapping("/{postId}/package") //"posts/2/package?id=5"
    public ResponseEntity<?> updatePackageOfPost (
            @PathVariable UUID postId,
            @RequestParam int id,
            @Valid @RequestBody UpdatePackageDto data
    ) throws ModuleException {

        PackageEntity result = this.postService.updatePackageByPost(postId, id, data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneById (
            @PathVariable UUID id
    )  {
        this.postService.softDelete(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }


    @GetMapping("/get-post-by-user")
    public ResponseEntity<?> getPostByUser (
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<PostEntity> result = this.postService.getPostsByUserId(userDetails.getUsername());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/get-post-by-major") //"posts/get-post-by-major?id"
    public ResponseEntity<?> getPostByMajor(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam int id
    ) {
        Page<PostEntity> result = this.postService.getPostsByMajorId(id, size, page);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/get-post-by-category") //"posts/get-post-by-major?id"
    public ResponseEntity<?> getPostByCategory(
            @RequestParam int id
    ) {
        List<PostEntity> result = this.postService.getPostsByCategoryId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById (
            @PathVariable UUID id
    ) {
        PostEntity result = this.postService.getOneById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllPosts(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) int majorId,
            @RequestParam(required = false) Boolean latest,
            @RequestParam(required = false) Boolean bestSeller,
            @RequestParam(required = false) Boolean topFeedback
    ) {
        Page<PostEntity> result = postService.getAllPosts(size, page, majorId, latest, bestSeller, topFeedback);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
