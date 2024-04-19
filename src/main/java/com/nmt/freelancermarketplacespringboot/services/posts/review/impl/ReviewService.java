package com.nmt.freelancermarketplacespringboot.services.posts.review.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.review.CreateReviewDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.review.IReviewRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import com.nmt.freelancermarketplacespringboot.services.posts.review.IReviewService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractBaseService<ReviewEntity, Integer> implements IReviewService {

    @Autowired
    IReviewRepository reviewRepository;

    @Autowired
    IUserService userService;

//    @Autowired
//    IOrderService postService;

    @Autowired
    public ReviewService(IReviewRepository reviewRepository) {
        super(reviewRepository);
    }


    @Override
    public ReviewEntity createOne(String email, CreateReviewDto data) {
        UserEntity checkUser = this.userService.getUserByEmail(email);
//        PostEntity findPost = this.postService.getOneById(data.postId());
        ReviewEntity createReview = new ReviewEntity();
        createReview.setRating(data.rating());
        createReview.setFeedback(data.feedback());
        //createReview.setOrder(findPost);
        createReview.setUser(checkUser);
        return this.reviewRepository.save(createReview);
    }
}
