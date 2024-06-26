package com.nmt.freelancermarketplacespringboot.entities.users.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
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
     * 3. experience
     */

    @OneToMany(mappedBy = "profileAttribute", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfileValueEntity> profileAttributeValues;

    // Getter and setter methods
}
