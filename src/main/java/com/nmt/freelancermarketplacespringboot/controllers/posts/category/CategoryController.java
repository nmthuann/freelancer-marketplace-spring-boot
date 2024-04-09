package com.nmt.freelancermarketplacespringboot.controllers.posts.category;


import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.controllers.posts.post.PostController;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.category.ICategoryService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//@PreAuthorize("hasRole('ADMIN')")
//@SecurityRequirement(name = "bearerAuth") // -> specific for each Controller.
@Tag(name = "Category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss");
    @Autowired
    ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @Operation(
        description = "",
        summary = "",
        responses = {
                @ApiResponse (
                        description = "Success",
                        responseCode = "200"
                ),
                @ApiResponse(
                        description = "Unauthorized",
                        responseCode = "401"
                )
        }
    )
    @GetMapping("/init")
    @Hidden
    public ResponseEntity<?> initCategory (
    ) {
        CategoryEntity result = this.categoryService.initCategory();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/create")
    // @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<?> createOne (
            @Valid @RequestBody CreateCategoryDto data
    ) throws ModuleException {
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


    @GetMapping("/")
    public ResponseEntity<?> getCategoriesByParentId (
            @RequestParam int id,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        List<CategoryEntity> result = this.categoryService.getCategoriesByParentId(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
//        response.getWriter().println(result);
//        response.flushBuffer();
//        int responseSize = response.getBufferSize();
        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(formatter);
        logger.info(String.format("%s - - [%s + 0000] \"%s %s HTTP/1.1\" 200 %s \"%s\" \"%s\"",
                request.getRemoteAddr(),
                LocalDateTime.now(),
                request.getMethod(),
                request.getRequestURI(),
                response.getBufferSize(),

                request.getHeader("Referer"),
                request.getHeader("User-Agent")));

        System.out.println("Log: " +    " " + request.getRemoteAddr() + "- -" +
                " " + LocalDateTime.now() +
                " " + request.getMethod() +
                " " + request.getRequestURI() +
                " " + 200 +
                " "  +
                " " + request.getHeader("User-Agent")
        );
        //return new ResponseEntity<>(result, HttpStatus.OK);
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/all")
//    public ResponseEntity<?> getCategoriesByParentIds () {
//        List<CategoryEntity> result = this.categoryService.getCategoriesByParentId(id);
//        if (result.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }




}
