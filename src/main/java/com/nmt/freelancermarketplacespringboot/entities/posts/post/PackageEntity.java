package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import jakarta.persistence.*;

@Entity
@Table(name = "Packages")
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Long packageId;

    @Column(name = "package_name", length = 50, nullable = false)
    private String packageName;

    @Column(name = "caption")
    private String caption;

    @Column(name = "revision", length = 255)
    private String revision;

    @Column(name = "delivery_day", nullable = false)
    private Integer deliveryDay;

    // @Column(name = "unit_price", nullable = false)
    // private Double unitPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    // Getter and setter methods
}
