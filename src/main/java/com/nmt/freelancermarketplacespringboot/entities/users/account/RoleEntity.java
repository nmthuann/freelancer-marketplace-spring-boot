package com.nmt.freelancermarketplacespringboot.entities.users.account;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    //@JsonManagedReference
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AccountEntity> accounts;

    // Getter and setter methods
    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleId=" + roleId +
                ", roleName='" + role_name + '\'' +
                '}';
    }
}