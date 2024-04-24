package com.nmt.freelancermarketplacespringboot.entities.posts.post;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Posts")
public class PostEntity  extends AbstractBaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id",  updatable = false, nullable = false)
    private UUID postId;

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false, length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'active'")
    private String status;

//    @Column(name = "FAQ", nullable = true) //columnDefinition = "TEXT DEFAULT ''"
    private String FAQ;


    @Column(name = "rating", nullable = false)
    private Float rating;


    @Column(name = "vote", nullable = false)
    private int vote;


//    @Column(name = "seller", nullable = false)
//    private String seller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_post_user"))
    @JsonIgnore
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_post_major"))
    @JsonIgnore
    private MajorEntity major;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    @JsonIgnore
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    @JsonIgnore
    private List<PackageEntity> packages;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReviewEntity> reviews;

    // Getter and setter methods
}
