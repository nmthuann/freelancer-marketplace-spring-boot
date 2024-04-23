package com.nmt.freelancermarketplacespringboot.entities.posts.review;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;


/**
 * customer
 * rating
 * feedback
 *
 */

@Data
@Entity
@Table(name = "Reviews")
public class ReviewEntity extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

//    @Column(name = "buyer", nullable = false)
//    private String buyer; // khóa ngoại

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "feedback", nullable = false)
    private String feedback;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_review_post"))
    private PostEntity post;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "order_id", nullable = false,
//            foreignKey = @ForeignKey(name = "fk_review_order"))
//    private OrderEntity order;
}
