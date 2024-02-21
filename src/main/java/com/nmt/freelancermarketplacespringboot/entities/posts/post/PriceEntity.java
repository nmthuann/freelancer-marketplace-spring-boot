package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Prices")
public class PriceEntity {

    @EmbeddedId
    private PriceId priceId;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("packageId")
    @JoinColumn(name = "package_id")
    private PackageEntity packageEntity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP(6)")
    private Date createdAt;
}

@Embeddable
class PriceId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long packageId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date beginAt;

    // Constructors, getters, setters, and other methods
}
