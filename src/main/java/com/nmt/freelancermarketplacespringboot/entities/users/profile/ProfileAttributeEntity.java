package com.nmt.freelancermarketplacespringboot.entities.users.profile;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ProfileAttributes")
public class ProfileAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_attribute_id")
    private Long profileAttributeId;

    @Column(name = "profile_attribute_name", nullable = false)
    private String profileAttributeName;
    /**
     * Ex:
     * 1. education,
     * 2. skill,
     * 3. experence
     */

    @OneToMany(mappedBy = "profileAttribute", cascade = CascadeType.ALL)
    private List<ProfileValueEntity> profileAttributeValues;

    // Getter and setter methods
}
