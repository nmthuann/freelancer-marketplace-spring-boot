package com.nmt.freelancermarketplacespringboot.entities.users.account;

import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class AccountEntity {
    @Id
    @Column(length = 50)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="status", columnDefinition = "BOOLEAN DEFAULT true")
    private boolean status = true;

    @Column(name="refresh_token")
    private String refreshToken;

    @Column(name= "sub")
    private String sub;

    @PrePersist
    @PreUpdate
    private void emailToLowerCase() {
        this.email = this.email.toLowerCase();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_account_role"))
    private RoleEntity role;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auth_method_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_account_auth_method"))
    private AuthMethodEntity authMethod;

    @OneToOne(mappedBy = "account")
    private UserEntity user;
}
