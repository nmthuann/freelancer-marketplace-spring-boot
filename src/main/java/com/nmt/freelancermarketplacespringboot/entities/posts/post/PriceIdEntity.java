package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Data
@Embeddable
public class PriceIdEntity implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

//    private int packageId;
    @ManyToOne(fetch = FetchType.EAGER)
    // @MapsId("packageId")
    @JoinColumn(name = "package_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_price_package"))
    private PackageEntity packageEntity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="begin_at")
    private Date beginAt;
}


//@ManyToOne(fetch = FetchType.EAGER)
//@MapsId("packageId")
//@JoinColumn(name = "package_id", nullable = false,
//        foreignKey = @ForeignKey(name = "fk_price_package"))
//private PackageEntity packageEntity;


