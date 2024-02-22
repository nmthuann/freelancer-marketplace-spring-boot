package com.nmt.freelancermarketplacespringboot.entities.users.account;

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

    @OneToMany(mappedBy = "authMethod")
    private List<AccountEntity> accounts;

}
