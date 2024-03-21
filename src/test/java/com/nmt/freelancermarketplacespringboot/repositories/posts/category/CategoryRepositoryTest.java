package com.nmt.freelancermarketplacespringboot.repositories.posts.category;

import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    @Autowired
    private ICategoryRepository categoryRepository;


    @Test
    void itShouldCheckIfCategoryIncrementRightValue() {
        //given
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(1);
        category.setCategoryName("ROOT");
        category.setLeftValue(1);
        category.setRightValue(2);
        category.setDescription("");
        categoryRepository.save(category);

        //when
        boolean expected = categoryRepository.existsById(1);

        //then
        Assertions.assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfCategoryIncrementLeftValue() {
    }
}