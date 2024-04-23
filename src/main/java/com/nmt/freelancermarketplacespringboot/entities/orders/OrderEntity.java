package com.nmt.freelancermarketplacespringboot.entities.orders;


import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "Orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id",  updatable = false, nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private String status;

    @Column(name="delivery_day", nullable = false)
    private Date deliveryDay;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private TransactionEntity transaction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reason_id")
    private ReasonEntity reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "package_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_package"))
    private PackageEntity packageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_seller"))
    private UserEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_buyer"))
    private UserEntity buyer;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<RevisionEntity> revisions;

}
