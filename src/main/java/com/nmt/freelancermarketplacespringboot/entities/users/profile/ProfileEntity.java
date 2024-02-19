package com.nmt.freelancermarketplacespringboot.entities.users.profile;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.persistence.*;

import java.util.List;

/**
 * level
 * occupation
 * created
 * status
 * attribute
 * value
 */

@Entity
@Table(name = "Profiles")
public class ProfileEntity extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "level", columnDefinition = "VARCHAR(50) DEFAULT 'NEW_SELLER'")
    private String level;

    @Column(name = "occupation")
    private String occupation;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<ProfileValueEntity> profileAttributeValues;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private UserEntity user;

}
