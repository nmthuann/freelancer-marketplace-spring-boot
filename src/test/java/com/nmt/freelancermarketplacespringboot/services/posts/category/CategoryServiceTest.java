package com.nmt.freelancermarketplacespringboot.services.posts.category;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.CategoryException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.CategoryExceptionMessage;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.category.ICategoryRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.category.impl.CategoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private ICategoryRepository categoryRepository;

    //private CategoryService categoryService;
    @InjectMocks
    private CategoryService categoryService;


    // private AutoCloseable autoCloseable;

    // Mocking mocking;
    @BeforeEach
    void setUp() {
        // Initialize mocks
//        autoCloseable = MockitoAnnotations.openMocks(this);
//
//        categoryService = new CategoryService(categoryRepository);
        MockitoAnnotations.openMocks(this);

        // Mocking data
        CategoryEntity parentCategory = new CategoryEntity();
        // parentCategory.setCategoryId(1);
        parentCategory.setCategoryName("ROOT");
        parentCategory.setLeftValue(1);
        parentCategory.setRightValue(2);

        // Mock repository method
        when(categoryRepository.save(parentCategory)).thenReturn(parentCategory);


    }


//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void canGetAllCategory() {
        // when
        categoryService.getAll();
        // then
        verify(categoryRepository).findAll();

    }

    @Test
    // @Disabled
    @DisplayName("testCreateOne created successfully")
    void canCreateOne() {
        // Mocking data
        CreateCategoryDto createCategoryDto = new CreateCategoryDto(0, "Test Category");

        // Mock repository method to return null for findById
        when(categoryRepository.findById(0)).thenReturn(null);

        // when and then
        assertThatThrownBy(() -> {
            categoryService.createOne(createCategoryDto);
        }).isInstanceOf(CategoryException.class)
                .hasMessageContaining(CategoryExceptionMessage.CATEGORY_PARENT_NOT_FOUND.getMessage());
    }

}