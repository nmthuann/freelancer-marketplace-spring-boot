package com.nmt.freelancermarketplacespringboot.entities.users.account;

import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
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
    private String refreshToken;

    @Column
    private String sub;

    @PrePersist
    @PreUpdate
    private void emailToLowerCase() {
        this.email = this.email.toLowerCase();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auth_method_id")
    private AuthMethodEntity authMethod;

//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public String getRefreshToken() {
//        return refreshToken;
//    }
//
//    public void setRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }
//
//    public String getSub() {
//        return sub;
//    }
//
//    public void setSub(String sub) {
//        this.sub = sub;
//    }
}
