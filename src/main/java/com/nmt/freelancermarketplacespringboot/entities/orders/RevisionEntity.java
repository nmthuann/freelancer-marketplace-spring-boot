package com.nmt.freelancermarketplacespringboot.entities.orders;


import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Revisions")
public class RevisionEntity extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revision_id")
    private int revisionId;

    @Column(name = "revision_content",nullable = false)
    private String revisionContent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
