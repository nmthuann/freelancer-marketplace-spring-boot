package com.nmt.freelancermarketplacespringboot.entities.users.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * level
 * occupation
 * created
 * status
 * attribute
 * value
 */
@Data
@Entity
@Table(name = "Profiles")
public class ProfileEntity extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int profileId;

    @Column(name = "level", columnDefinition = "VARCHAR(50) DEFAULT 'NEW_SELLER'")
    private String level;

    @Column(name = "occupation")
    private String occupation;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfileValueEntity> profileAttributeValues;

    @OneToOne(mappedBy = "profile")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private UserEntity user;
}
