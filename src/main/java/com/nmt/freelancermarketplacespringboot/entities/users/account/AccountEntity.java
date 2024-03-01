package com.nmt.freelancermarketplacespringboot.entities.users.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_account_role"))
    private RoleEntity role;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auth_method_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_account_auth_method"))
    private AuthMethodEntity authMethod;

//    @JsonManagedReference
    @JsonIgnore
    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private UserEntity user;
    @Override
    public String toString() {
        return "AccountEntity{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", status=" + status +
                ", refreshToken='" + refreshToken + '\'' +
                ", sub='" + sub + '\'' +
                ", role=" + (role != null ? role.getRole_name() : null) +
                ", authMethod=" + (authMethod != null ? authMethod.getAuthMethodName() : null) +
                ", user=" + (user != null ? user.getFirstName() : null) +
                '}';
    }

}
