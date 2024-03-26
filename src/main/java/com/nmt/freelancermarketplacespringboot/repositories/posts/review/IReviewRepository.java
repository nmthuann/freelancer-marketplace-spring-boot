package com.nmt.freelancermarketplacespringboot.repositories.posts.review;

import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends CrudRepository<ReviewEntity, Integer> {
}
