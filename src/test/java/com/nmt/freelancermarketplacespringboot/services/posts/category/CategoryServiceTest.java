package com.nmt.freelancermarketplacespringboot.services.posts.category;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.posts.CategoryException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts.CategoryExceptionMessage;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.category.ICategoryRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.category.impl.CategoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private ICategoryRepository categoryRepository;

    //private CategoryService categoryService;
    @InjectMocks
    private CategoryService categoryService;


    // private AutoCloseable autoCloseable;

    private ArrayList<CategoryEntity> categoryEntitiesList;


    // Mocking mocking;
    @BeforeEach
    void setUp() {
        // Initialize mocks
//        autoCloseable = MockitoAnnotations.openMocks(this);
//
//        categoryService = new CategoryService(categoryRepository);
        MockitoAnnotations.openMocks(this);

        categoryEntitiesList = new ArrayList<>();

//        categoryEntitiesList.add(
//                new CategoryEntity(1, "ROOT", "",1, 10,null));
//        categoryEntitiesList.add(
//                new CategoryEntity(2, "Graphic and Design", "",2, 3, null));
//        categoryEntitiesList.add(
//                new CategoryEntity(3, "Digital Marketing", "",4, 5, null));
//        categoryEntitiesList.add(
//                new CategoryEntity(4, "Programing and Tech","", 6, 7, null));
//        categoryEntitiesList.add(
//                new CategoryEntity(5, "Photography", "",8, 9, null));

        // for (CategoryEntity category : categoryEntitiesList) {
            // categoryRepository.saveAll(categoryEntitiesList);
        when(categoryRepository.saveAll(categoryEntitiesList)).thenReturn(categoryEntitiesList);

        // }

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
        CreateCategoryDto createCategoryDto = new CreateCategoryDto(2, "AB", "");

        // Mock repository method to return null for findById
        when(categoryRepository.findById(2)).thenReturn(categoryRepository.findById(2));

        // when and then
        assertThatThrownBy(() -> {
            categoryService.createOne(createCategoryDto);
        }).isInstanceOf(CategoryException.class)
                .hasMessageContaining(CategoryExceptionMessage.CATEGORY_PARENT_NOT_FOUND.getMessage());
    }

}