package com.nmt.freelancermarketplacespringboot.entities.posts.major;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "MajorValues")
public class MajorValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "value_id")
    private int valueId;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id")
    private MajorEntity major;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_attribute_id")
    private MajorAttributeEntity majorAttribute;

}
