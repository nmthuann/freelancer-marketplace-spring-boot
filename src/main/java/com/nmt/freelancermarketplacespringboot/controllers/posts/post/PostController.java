package com.nmt.freelancermarketplacespringboot.controllers.posts.post;


import com.nmt.freelancermarketplacespringboot.dto.posts.post.CreatePostDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.UpdatePostDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * 1. create Post
 * 2. update Post
 * 3. getOneById
 * 4. getAll
 * 5. getPostByUser
 * 6. getPostByMajor
 *
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
    ) {
        System.out.println(data.toString());
        PostEntity result = this.postService.createOne(userDetails.getUsername(), data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateOneById (
            @Valid @RequestBody UpdatePostDto data,
            @PathVariable UUID id
    ) {
        PostEntity result = this.postService.updateOneById(id, data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
