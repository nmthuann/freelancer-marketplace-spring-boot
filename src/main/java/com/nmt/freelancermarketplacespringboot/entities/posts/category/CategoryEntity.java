package com.nmt.freelancermarketplacespringboot.entities.posts.category;


import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class CategoryEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT DEFAULT ''")
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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MajorEntity> majors;

    // Getter and setter methods
}
