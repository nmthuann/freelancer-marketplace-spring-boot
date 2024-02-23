package com.nmt.freelancermarketplacespringboot.entities.orders;

import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "Transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id",  updatable = false, nullable = false)
    private UUID transactionId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;



    @Column(nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethodEntity paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private UserEntity user;

    @Column()
    private String description;

    @Column(nullable = false)
    private String status;

}
