package com.nmt.freelancermarketplacespringboot.modules.account.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Accounts")
public class AccountEntity {
    @Id
    @Column(length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean status = true;

    @Column
    private String refresh_token;

    @Column
    private String sub;

    @PrePersist
    @PreUpdate
    private void emailToLowerCase() {
        this.email = this.email.toLowerCase();
    }
}
