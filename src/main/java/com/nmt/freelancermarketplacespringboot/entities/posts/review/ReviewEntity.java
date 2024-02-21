package com.nmt.freelancermarketplacespringboot.entities.posts.review;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import jakarta.persistence.*;


/**
 * customer
 * rating
 * feedback
 *
 */

@Entity
@Table(name = "Reviews")
public class ReviewEntity extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "buyer", nullable = false)
    private String buyer;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "feedback", nullable = false)
    private String feedback;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
