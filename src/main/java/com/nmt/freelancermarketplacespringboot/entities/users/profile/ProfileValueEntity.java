package com.nmt.freelancermarketplacespringboot.entities.users.profile;

import jakarta.persistence.*;


@Entity
@Table(name = "ProfileValues")
public class ProfileValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_value_id")
    private Long profileValueId;

    @Column(name = "profile_value", nullable = false)
    private String profileValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_attribute_id")
    private ProfileAttributeEntity profileAttribute;

    // Getter and setter methods
}
