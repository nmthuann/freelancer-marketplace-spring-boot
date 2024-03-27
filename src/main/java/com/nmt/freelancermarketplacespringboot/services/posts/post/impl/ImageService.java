package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.ImageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.InsertImagesDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IImageRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IImageService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.ArrayList;
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
    @Transactional()
    public List<ImageEntity> insertImages(PostEntity postCreated, List<ImageDto> data) {

        // PostEntity findPost = this.postService.getOneById(data.postId());

//        for (ImageDto imageDto: data){
//            ImageEntity newImage = new ImageEntity();
//            newImage.setUrl(imageDto.url());
//            newImage.setPost(postCreated);
//            this.imageRepository.save(newImage);
//        }
//
//        return this.imageRepository.findByPost(postCreated);

        List<ImageEntity> insertedImages = new ArrayList<>();

        try {
            for (ImageDto imageDto : data) {
                ImageEntity newImage = new ImageEntity();
                newImage.setUrl(imageDto.url());
                newImage.setPost(postCreated);
                insertedImages.add(imageRepository.save(newImage));
            }
        } catch (Exception e) {
            // Handle the error appropriately
            // For example, log the error or throw a custom exception
            // You can also perform any cleanup or recovery actions here
            // For now, re-throwing the exception
            throw e;
        }

        return insertedImages;
    }

    @Override
    @Transactional
    public List<ImageEntity> updateImages(PostEntity postUpdated, List<ImageDto> data) {
        List<ImageEntity> removedImages = new ArrayList<>();
        List<ImageDto> noChangeList = new ArrayList<>();
        List<ImageDto> insertList = new ArrayList<>();

        List<ImageEntity> imageEntities = this.imageRepository.findByPost(postUpdated);


        for (ImageEntity imageEntity : imageEntities) {
            boolean found = false;
            for (ImageDto imageDto : data) {
                if (imageEntity.getUrl().equals(imageDto.url())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                removedImages.add(imageEntity);
            }
        }

        // Check for images to be inserted
        for (ImageDto imageDto : data) {
            boolean found = false;
            for (ImageEntity imageEntity : imageEntities) {
                if (imageEntity.getUrl().equals(imageDto.url())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                insertList.add(imageDto);
            }
        }

        // Insert new images
        List<ImageEntity> createdImages = this.insertImages(postUpdated, insertList);

        // Remove images that need to be deleted
        for (ImageEntity removedImage : removedImages) {
            imageRepository.delete(removedImage);
        }

        return imageRepository.findByPost(postUpdated);
    }
}






//        int indexOfImageEntities = 0, indexOfImageDto = 0;
//
//        while(indexOfImageEntities < imageEntities.size() && indexOfImageDto < data.size() ){
//            if(imageEntities.get(indexOfImageEntities).getUrl().equals(data.get(indexOfImageDto).url())){
//                result.add(data.get(indexOfImageDto));
//                indexOfImageEntities++;
//                indexOfImageDto++;
//            } else if () {
//
//            }
//        }