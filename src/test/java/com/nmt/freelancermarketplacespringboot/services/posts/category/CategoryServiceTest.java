package com.nmt.freelancermarketplacespringboot.services.posts.category;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.CategoryException;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.category.ICategoryRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.category.impl.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

//    @BeforeEach
//    public void setUp() {
//        categoryRepository = mock(ICategoryRepository.class);
//        categoryService = new CategoryService(categoryRepository);
//    }

    // Mocking mocking;
    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
        // mocking = new Mocking();
    }

    @Test
    @DisplayName("testCreateOne created successfully")
    public void testCreateOne() throws CategoryException {
        // Mocking data

        CategoryEntity parentCategory = new CategoryEntity();
        parentCategory.setCategoryId(1);
        parentCategory.setLeftValue(1);
        parentCategory.setRightValue(2);


        when(categoryRepository.findById(Integer.valueOf(Mockito.anyString()))).thenReturn(Optional.of(parentCategory));

        CreateCategoryDto createCategoryDto = new CreateCategoryDto(1, "Test Category");
        // Execute service method
        CategoryEntity createdCategory = categoryService.createOne(createCategoryDto);

        // Assertions
        assertEquals("Test Category", createdCategory.getCategoryName());
        assertEquals(2, createdCategory.getCategoryId());
        assertEquals(3, createdCategory.getLeftValue());
        assertEquals(4, createdCategory.getRightValue());
    }

}
//        // Mocking repository behavior
//        when(categoryRepository.findById(1)).thenReturn(Optional.of(parentCategory));
//
//        // Mocking repository method behavior
//        when(categoryRepository.save(Mockito.any(CategoryEntity.class))).thenAnswer(invocation -> {
//            CategoryEntity entityToSave = invocation.getArgument(0);
//            entityToSave.setCategoryId(2); // Mocking the saved entity's ID
//            return entityToSave;
//        });
//
//        // Test the service method
//        CategoryEntity createdCategory = categoryService.creatOne(createCategoryDto);
//
//        // Assertions
//        assertNotNull(createdCategory);
//        assertEquals("Test Category", createdCategory.getCategoryName());
//        assertEquals(2, createdCategory.getCategoryId()); // Asserting the saved entity's ID




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
