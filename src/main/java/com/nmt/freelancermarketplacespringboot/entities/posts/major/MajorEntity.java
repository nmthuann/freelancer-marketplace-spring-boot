package com.nmt.freelancermarketplacespringboot.entities.posts.major;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
// import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Majors")
public class MajorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private int majorId;

    @Column(name = "major_name", nullable = false) // service_type
    private String majorName;

    @ManyToOne(fetch = FetchType.EAGER) //fetch = FetchType.EAGER
    @JoinColumn(name = "category_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_major_category"))
    private CategoryEntity category;

    @JsonIgnore
    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
    private List<MajorValueEntity> majorValues;

    @JsonIgnore
    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
    private List<PostEntity> posts;
}
