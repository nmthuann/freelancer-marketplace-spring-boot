package com.nmt.freelancermarketplacespringboot.entities.posts.major;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MajorAttributes")
public class MajorAttributeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_attribute_id")
    private Long majorAttributeId;

    @Column(name = "major_attribute_name", nullable = false)
    private String majorAttributeName;

    @OneToMany(mappedBy = "majorAttribute", cascade = CascadeType.ALL)
    private List<MajorValueEntity> majorAttributeValues;
}
