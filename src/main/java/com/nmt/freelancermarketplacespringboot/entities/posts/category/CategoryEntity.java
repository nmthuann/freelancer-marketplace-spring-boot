package com.nmt.freelancermarketplacespringboot.entities.posts.category;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false, nullable = false)
    private int categoryId;

    @Column(name = "category_name", nullable = false, unique = true) // index in here
    private String categoryName;

    @Column(name = "description", nullable = true) // columnDefinition = "TEXT DEFAULT ''"
    private String description;

    /**
     * Kỹ thuật: Nested Set Model
     * (1, 'Electronics', 1, 10),
     * (2, 'Mobile Phones', 2, 5),
     * (3, 'Laptops', 6, 9),
     * (4, 'Smartphones', 3, 4),
     * (5, 'Gaming Laptops', 7, 8);
     */

    @Column(name = "left_value", nullable = false)
    private int leftValue;

    @Column(name = "right_value", nullable = false)
    private int rightValue;

    @Column(name = "parent_id", nullable = false)
    private int parentId;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MajorEntity> majors;

    // Getter and setter methods
}
