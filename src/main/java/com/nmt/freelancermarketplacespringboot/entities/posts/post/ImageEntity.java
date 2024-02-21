package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import jakarta.persistence.*;

@Entity
@Table(name = "Images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(name = "url", nullable = false)
    private String url;

    // Getter and setter methods
}
