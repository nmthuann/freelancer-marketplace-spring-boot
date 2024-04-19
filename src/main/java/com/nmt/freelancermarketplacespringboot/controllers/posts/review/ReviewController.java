package com.nmt.freelancermarketplacespringboot.controllers.posts.review;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.dto.posts.review.CreateReviewDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.review.IReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. getReviewsByPostId
 * 2. createReview
 *
 *
 */
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    IReviewService reviewService;

    // logging

    public ReviewController(IReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOne (
            @Valid @RequestBody CreateReviewDto data,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws ModuleException {
        ReviewEntity result = this.reviewService.createOne(userDetails.getUsername(), data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
