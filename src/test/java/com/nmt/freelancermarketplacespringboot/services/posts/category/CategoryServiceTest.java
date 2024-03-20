package com.nmt.freelancermarketplacespringboot.services.posts.category;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.CategoryException;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.category.ICategoryRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.category.impl.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private CategoryService categoryService;
    private ICategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryRepository = mock(ICategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void testCreateOne() throws CategoryException {
        // Arrange
        CreateCategoryDto createCategoryDto
                = new CreateCategoryDto(1, "Test Category");
        // Provide categoryParentId and categoryName


        CategoryEntity parentCategory = new CategoryEntity();
        parentCategory.setCategoryId(1);
        parentCategory.setLeftValue(1);
        parentCategory.setRightValue(2);

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(parentCategory));

        // Act
        CategoryEntity createdCategory = categoryService.creatOne(createCategoryDto);

        // Assert
        // Add assertions as per your requirement
        assertNotNull(createdCategory);
        assertEquals("Test Category", createdCategory.getCategoryName());
        assertEquals(2, createdCategory.getLeftValue()); // Assuming increment by 2
        assertEquals(3, createdCategory.getRightValue()); // Assuming increment by 2
    }

}

//
//    @Mock
//    ICategoryRepository categoryRepository;
//
//
//    @Test
//    void whenCreateOneShouldReturnNewCategory() {
//        // 1. create mock data
//        CategoryEntity root = new CategoryEntity();
//        root.setCategoryId(1);
//        root.setCategoryName("ROOT");
//        root.setLeftValue(1);
//        root.setRightValue(2);
//        root.setDescription("");
//
//        int parentNodeId = 1;
//        String categoryName = "Test Category";
//
//
//        // 2. define behavior of Repository
//        when(root.getCategoryId() == parentNodeId).
//
//
//    }
