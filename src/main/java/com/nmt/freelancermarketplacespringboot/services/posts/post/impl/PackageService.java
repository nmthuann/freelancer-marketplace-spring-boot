package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.CreatePackageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PackageDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IPackageRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPackageService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PackageService extends AbstractBaseService<PackageEntity, Integer> implements IPackageService {

    @Autowired
    IPackageRepository packageRepository;



    @Autowired
    public PackageService(IPackageRepository packageRepository) {
        super(packageRepository);
    }

    /**
     * add set up price entity.
     * @param postCreated
     * @param data
     * @return
     */
    @Override
    public List<PackageEntity> createPackageByPost(PostEntity postCreated, List<PackageDto> data) {

        // PostEntity findPost = this.postService.getOneById(postId);

        for (PackageDto packageDto: data){
            PackageEntity newPackage = new PackageEntity();
            newPackage.setPackageName(packageDto.packageName());
            newPackage.setRevision(packageDto.revision());
            newPackage.setCaption(packageDto.caption());
            newPackage.setDeliveryDay(packageDto.deliveryDay());
            newPackage.setPost(postCreated);
            this.packageRepository.save(newPackage);
        }

        return this.packageRepository.findByPost(postCreated);
    }
}


