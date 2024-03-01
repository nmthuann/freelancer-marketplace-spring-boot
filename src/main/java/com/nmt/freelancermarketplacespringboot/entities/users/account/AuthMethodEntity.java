package com.nmt.freelancermarketplacespringboot.entities.users.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name="AuthMethods")
public class AuthMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_method_id")
    private int authMethodId;

    @Column(name = "auth_method_name", nullable = false)
    private String authMethodName;

    @Column(name = "description")
    private String description;

    // @JsonManagedReference
    @OneToMany(mappedBy = "authMethod", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AccountEntity> accounts;

    @Override
    public String toString() {
        return "AuthMethodEntity{" +
                "authMethodId=" + authMethodId +
                ", authMethodName='" + authMethodName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
