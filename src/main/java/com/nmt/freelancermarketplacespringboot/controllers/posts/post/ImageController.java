package com.nmt.freelancermarketplacespringboot.controllers.posts.post;


import com.nmt.freelancermarketplacespringboot.dto.posts.post.InsertImagesDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.post.impl.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {


    @Autowired
    ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


//    @PostMapping("/insert-images")
//    public ResponseEntity<?> insertImages (
//            @Valid @RequestBody InsertImagesDto data
//            // @AuthenticationPrincipal UserDetails userDetails
//    ) {
//        System.out.println(data.toString());
//        List<ImageEntity> result = this.imageService.insertImages(data);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }



}
