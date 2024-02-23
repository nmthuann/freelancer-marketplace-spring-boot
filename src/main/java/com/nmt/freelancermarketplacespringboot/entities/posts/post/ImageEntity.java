package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "Images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "image_id")
    private UUID imageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_image_post"))
    private PostEntity post;

    @Column(name = "url", nullable = false)
    private String url;

    // Getter and setter methods
}
