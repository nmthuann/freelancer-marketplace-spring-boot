package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.ImageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.InsertImagesDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IImageRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IImageService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ImageService extends AbstractBaseService<ImageEntity, UUID> implements IImageService {

    @Autowired
    IImageRepository imageRepository;

//    @Autowired
//    IPostService postService;

    @Autowired
    public ImageService(IImageRepository imageRepository) {
        super(imageRepository);
    }

    @Override
    public List<ImageEntity> insertImages(PostEntity postCreated, List<ImageDto> data) {

        // PostEntity findPost = this.postService.getOneById(data.postId());

        for (ImageDto imageDto: data){
            ImageEntity newImage = new ImageEntity();
            newImage.setUrl(imageDto.url());
            newImage.setPost(postCreated);
            this.imageRepository.save(newImage);
        }

        return this.imageRepository.findByPost(postCreated);
    }
}
