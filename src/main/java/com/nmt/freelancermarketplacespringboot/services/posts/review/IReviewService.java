package com.nmt.freelancermarketplacespringboot.services.posts.review;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.review.CreateReviewDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;

public interface IReviewService extends IBaseService<ReviewEntity, Integer> {

    ReviewEntity createOne(String email, CreateReviewDto data);
}
