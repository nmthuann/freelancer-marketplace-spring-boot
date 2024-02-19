package com.nmt.freelancermarketplacespringboot.entities.users.user;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "UserPayments")
public class UserPaymentEntity {

    @Id
    @Column(name = "user_payment_id", columnDefinition = "uuid", nullable = false)
    private UUID userPaymentId;

    @Column(name = "card_number", nullable = false, length = 15, unique = true)
    private String cardNumber;

    @Column(name = "cardholder_name", nullable = false, length = 50)
    private String cardholderName;

    @Column(name = "expired", nullable = false)
    private Date expired;

    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToOne(mappedBy = "userPayment", cascade = CascadeType.ALL)
    private UserEntity user;

    // Getter and setter methods
}