package com.nmt.freelancermarketplacespringboot.entities.posts.major;


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

    @Column(name = "major_name", nullable = false)
    private String majorName;

    @ManyToOne(fetch = FetchType.EAGER) //fetch = FetchType.EAGER
    @JoinColumn(name = "category_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_major_category"))
    private CategoryEntity category;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
    private List<MajorValueEntity> majorValues;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
    private List<PostEntity> posts;
}
