package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.posts.ImageException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts.ImageExceptionMessage;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.ImageDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IImageRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
    @Transactional
    public List<ImageEntity> insertImages(PostEntity postCreated, List<ImageDto> data) throws ImageException {
        try {
            List<ImageEntity> insertedImages = new ArrayList<>();
            for (ImageDto imageDto : data) {
                ImageEntity newImage = new ImageEntity();
                newImage.setUrl(imageDto.url());
                newImage.setPost(postCreated);
                insertedImages.add(imageRepository.save(newImage));
            }
            return insertedImages;
        } catch (Exception e) {
            throw new ImageException(ImageExceptionMessage.INSERT_IMAGES_FAIL.getMessage());
        }
    }

    @Override
    @Transactional
    public List<ImageEntity> updateImages(PostEntity postUpdated, List<ImageDto> data) throws ImageException {
        List<ImageEntity> removedImages = new ArrayList<>();
        List<ImageDto> insertList = new ArrayList<>();
        List<ImageEntity> imageEntities = this.imageRepository.findByPost(postUpdated);

        try {

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

        } catch (ImageException e) {
            List<ImageEntity> imagesInDatabase = this.imageRepository.findByPost(postUpdated);
            for (ImageDto imageInsertFail: insertList){
                for (ImageEntity imageEntity: imagesInDatabase){
                    if (imageEntity.getUrl().equals(imageInsertFail.url())){
                        imageRepository.delete(imageEntity);
                    }
                }

            }
            throw new ImageException("Update fail!");
        }
        catch (IllegalArgumentException ex){

            for (ImageEntity imageEntity : imageEntities) {
                boolean shouldBeRemoved = true;
                for (ImageEntity removeFail : removedImages) {
                    if (imageEntity.getUrl().equals(removeFail.getUrl())) {
                        shouldBeRemoved = false;
                        break;
                    }
                }
                if (shouldBeRemoved) {
                    imageRepository.save(imageEntity);
                }
            }
            throw new ImageException("Update fail!");
        }
    }
}






//            List<ImageEntity> imagesInDatabase = this.imageRepository.findByPost(postUpdated);
//            for (ImageEntity imageEntity: imagesInDatabase){
//                for (ImageEntity removeFail: removedImages){
//                    if (! imageEntity.getUrl().equals(removeFail.getUrl())){
//                        imageRepository.save(imageEntity);
//                    }
//                }
//
//            }
//            throw ex;





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