package com.nmt.freelancermarketplacespringboot.repositories.posts.category;

import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private ICategoryRepository categoryRepository;


    @AfterEach
    void tearDown() {
        // categoryRepository.deleteAll();
    }

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