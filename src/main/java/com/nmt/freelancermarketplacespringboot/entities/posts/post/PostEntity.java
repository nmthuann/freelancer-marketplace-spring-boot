package com.nmt.freelancermarketplacespringboot.entities.posts.post;


import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Posts")
public class PostEntity extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", length = 36)
    private String postId;

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false, length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'active'")
    private String status;

    @Column(name = "FAQ", nullable = true, columnDefinition = "TEXT DEFAULT ''")
    private String FAQ;


//    @Column(name = "seller", nullable = false)
//    private String seller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<ImageEntity> images;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id")
    private MajorEntity major;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PackageEntity> packages;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews;

    // Getter and setter methods
}
