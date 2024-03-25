package com.nmt.freelancermarketplacespringboot.controllers.posts.major;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.CategoryException;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.major.CreateMajorDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 1. getMajorByCategoryId
 * 2. createMajor
 * 3. updateMajor
 */
@RestController
@RequestMapping("majors")
public class MajorController {
    @Autowired
    IMajorService majorService;

    public MajorController(IMajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping("/category")
    public ResponseEntity<?> getMajorsByCategoryId (
            @RequestParam int id
    ) {
        List<MajorEntity> result = this.majorService.getMajorsByCategory(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOne (
            @Valid @RequestBody CreateMajorDto data
    ) {
        System.out.println(data.toString());
        MajorEntity result = this.majorService.createOne(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateOneById (
//            @Valid @RequestBody CreateCategoryDto data,
//            @PathVariable int id
//    ) {
//        System.out.println(data.toString());
//        String result = "";
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
