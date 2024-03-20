package com.nmt.freelancermarketplacespringboot.controllers.posts.category;


import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.category.ICategoryService;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }



//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOneById(@PathVariable Integer id) {
//        CategoryEntity result = categoryService.getOneById(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
