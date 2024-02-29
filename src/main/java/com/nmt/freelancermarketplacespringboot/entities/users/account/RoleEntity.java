package com.nmt.freelancermarketplacespringboot.entities.users.account;



import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "Roles")
// @Where(clause = "deleted = false")
public class RoleEntity extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name", nullable = false)
    private String role_name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;

    // Getter and setter methods
}