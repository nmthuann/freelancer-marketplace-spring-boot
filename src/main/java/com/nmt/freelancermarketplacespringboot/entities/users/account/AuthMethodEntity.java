package com.nmt.freelancermarketplacespringboot.entities.users.account;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="AuthMethods")
public class AuthMethodEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_method_id")
    private int authMethodId;

    @Column(name = "auth_method_name", nullable = false)
    private String authMethodName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "authMethod")
    private List<AccountEntity> accounts;

    public int getAuthMethodId() {
        return authMethodId;
    }

    public void setAuthMethodId(int authMethodId) {
        this.authMethodId = authMethodId;
    }

    public String getAuthMethodName() {
        return authMethodName;
    }

    public void setAuthMethodName(String authMethodName) {
        this.authMethodName = authMethodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }
}
