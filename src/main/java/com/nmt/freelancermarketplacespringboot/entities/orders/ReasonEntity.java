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
    private Long reasonId;

    @Column(name="description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "reason", cascade = CascadeType.ALL)
    private OrderEntity order;



}
