package com.nmt.freelancermarketplacespringboot.repositories.posts.category;

import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE CategoryEntity  c SET c.rightValue = c.rightValue + 2 WHERE c.rightValue >= :rightValue")
    void incrementRightValue(int rightValue);


    @Transactional
    @Modifying
    @Query("UPDATE CategoryEntity  c SET c.leftValue = c.leftValue + 2 WHERE c.leftValue > :rightValue")
    void incrementLeftValue(int rightValue);

    List<CategoryEntity> findByParentId(int parentId);

}
