package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Prices")
public class PriceEntity {

    @EmbeddedId
    private PriceIdEntity priceId;

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
