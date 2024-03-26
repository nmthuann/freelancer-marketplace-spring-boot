package com.nmt.freelancermarketplacespringboot.services.posts.review.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.review.IReviewRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractBaseService<ReviewEntity, Integer> implements IReviewService {

    @Autowired
    public ReviewService(IReviewRepository reviewRepository) {
        super(reviewRepository);
    }
}
