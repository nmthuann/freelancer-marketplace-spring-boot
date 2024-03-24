package com.nmt.freelancermarketplacespringboot.entities.posts.major;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "MajorAttributes")
public class MajorAttributeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_attribute_id")
    private int majorAttributeId;

    @Column(name = "major_attribute_name", nullable = false)
    private String majorAttributeName;

    @JsonIgnore
    @OneToMany(mappedBy = "majorAttribute", cascade = CascadeType.ALL)
    private List<MajorValueEntity> majorAttributeValues;
}
