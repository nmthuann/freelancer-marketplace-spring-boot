package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IPackageRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService extends AbstractBaseService<PackageEntity, Integer> implements IPackageService {

    @Autowired
    public PackageService(IPackageRepository packageRepository) {
        super(packageRepository);
    }
}
