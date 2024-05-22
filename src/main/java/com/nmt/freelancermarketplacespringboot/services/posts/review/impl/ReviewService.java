package com.nmt.freelancermarketplacespringboot.services.posts.review.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.review.CreateReviewDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.review.IReviewRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPackageService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import com.nmt.freelancermarketplacespringboot.services.posts.review.IReviewService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractBaseService<ReviewEntity, Integer> implements IReviewService {
    private final IReviewRepository reviewRepository;
    private final IUserService userService;

    private final IPackageService packageService;

    @Autowired
    public ReviewService(
            IReviewRepository reviewRepository,
            IUserService userService,
            IPackageService packageService
    ) {
        super(reviewRepository);
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.packageService = packageService;
    }



    @Override
    public ReviewEntity createOne(String email, CreateReviewDto data) {
        UserEntity findUser = this.userService.getUserByEmail(email);
        PackageEntity findPackage = this.packageService.getOneById(data.packageId());
        ReviewEntity createReview = new ReviewEntity();
        createReview.setRating(data.rating());
        createReview.setFeedback(data.feedback());
        createReview.setImageUrl(data.imageUrl());
        createReview.setPackageEntity(findPackage);
        createReview.setUser(findUser);
        return this.reviewRepository.save(createReview);
    }
}
