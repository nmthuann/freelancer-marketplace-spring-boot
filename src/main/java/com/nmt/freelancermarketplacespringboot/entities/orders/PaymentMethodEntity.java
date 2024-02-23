package com.nmt.freelancermarketplacespringboot.entities.orders;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "PaymentMethods")
public class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private int paymentMethodId;

    @Column(name="payment_method_name", nullable = false)
    private String paymentMethodName;


    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;
}
