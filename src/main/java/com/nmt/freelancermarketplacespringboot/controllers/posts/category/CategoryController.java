package com.nmt.freelancermarketplacespringboot.controllers.posts.category;


import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.CategoryException;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.category.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 0. initCategory
 * 1. createOne
 * 2. getAll
 * 3. getById
 * 4. updateOneById
 * 5. getCategoriesByParentId
 *
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/init")
    public ResponseEntity<?> initCategory (
    ) {
        CategoryEntity result = this.categoryService.initCategory();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOne (
            @Valid @RequestBody CreateCategoryDto data
    ) throws CategoryException {
        System.out.println(data.toString());
        CategoryEntity result = this.categoryService.createOne(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateOneById (
            @Valid @RequestBody CreateCategoryDto data,
            @PathVariable int id
    ) {
        CategoryEntity result = this.categoryService.updateOneById(id, data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getOneById( @PathVariable int id) {
        CategoryEntity result = this.categoryService.getOneById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/get-categories-by-parent-id")
    public ResponseEntity<?> getCategoriesByParentId (
            @RequestParam int id

    ) {
        List<CategoryEntity> result = this.categoryService.getCategoriesByParentId(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
