package com.nmt.freelancermarketplacespringboot.entities.users.profile;

import jakarta.persistence.*;
import lombok.Data;

@Data
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
    @JoinColumn(name = "profile_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_value_profile"))
    private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_attribute_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_value_attribute_profile"))
    private ProfileAttributeEntity profileAttribute;

    // Getter and setter methods
}
