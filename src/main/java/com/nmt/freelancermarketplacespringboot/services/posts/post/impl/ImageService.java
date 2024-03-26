package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IImageRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageService extends AbstractBaseService<ImageEntity, UUID> implements IImageService {

//    @Autowired
//    IImageRepository imageRepository;

    @Autowired
    public ImageService(IImageRepository imageRepository) {
        super(imageRepository);
    }
}
