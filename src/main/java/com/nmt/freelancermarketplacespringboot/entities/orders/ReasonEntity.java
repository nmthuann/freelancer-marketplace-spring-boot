package com.nmt.freelancermarketplacespringboot.entities.orders;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Reasons")
public class ReasonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reason_id")
    private int reasonId;

    @Column(nullable = false)
    private String reason;

    @OneToOne(mappedBy = "reason")
    private OrderEntity order;

}
