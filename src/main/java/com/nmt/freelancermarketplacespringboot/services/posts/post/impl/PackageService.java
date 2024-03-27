package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PackageDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceIdEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IPackageRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPackageService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PackageService extends AbstractBaseService<PackageEntity, Integer> implements IPackageService {

    @Autowired
    IPackageRepository packageRepository;

    @Autowired
    IPriceService priceService;


    @Autowired
    public PackageService(IPackageRepository packageRepository) {
        super(packageRepository);
    }

    /**
     * add set up price entity.
     * @param postCreated
     * @param data
     * @return
     * => using Transaction.
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
            PackageEntity packageCreated = this.packageRepository.save(newPackage);

            PriceIdEntity newPriceId = new PriceIdEntity();
            newPriceId.setBeginAt(new Date());
            newPriceId.setPackageEntity(packageCreated);

            PriceEntity newPrice = new PriceEntity();
            newPrice.setPriceId(newPriceId);
            newPrice.setUnitPrice(packageDto.unitPrice());

            this.priceService.createOne(newPrice);
        }

        return this.packageRepository.findByPost(postCreated);
    }
}


