package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Packages")
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private int packageId;

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
    @JoinColumn(name = "post_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_package_post"))
    @JsonIgnore
    private PostEntity post;


    @JsonIgnore
    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;
}


//@OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
//private List<PriceIdEntity> prices;