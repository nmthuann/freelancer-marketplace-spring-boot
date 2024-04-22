package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.PackageDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.UpdatePackageDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceIdEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IPackageRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPackageService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPriceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PackageService
        extends AbstractBaseService<PackageEntity, Integer>
            implements IPackageService {

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
    @Transactional
    public List<PackageEntity> createPackageByPost(PostEntity postCreated, List<PackageDto> data) {

        // PostEntity findPost = this.postService.getOneById(postId);

        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return this.packageRepository.findByPost(postCreated);
    }


    @Override
    @Transactional
    public PackageEntity updateOneById(
            PackageEntity packageCreated,
            UpdatePackageDto data
    ) {
        try {
            packageCreated.setPackageName(data.packageName());
            packageCreated.setCaption(data.caption());
            packageCreated.setDeliveryDay(data.deliveryDay());
            packageCreated.setRevision(data.revision());
            PackageEntity packageUpdated = this.packageRepository.save(packageCreated);

            PriceIdEntity newPriceId = new PriceIdEntity();
            newPriceId.setBeginAt(data.beginAt());
            newPriceId.setPackageEntity(packageCreated);

            PriceEntity newPrice = new PriceEntity();
            newPrice.setPriceId(newPriceId);
            newPrice.setUnitPrice(data.unitPrice());

            this.priceService.createOne(newPrice);

            return packageUpdated;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PackageEntity> getPackagesByPostId(PostEntity postCreated) {
        return this.packageRepository.findByPost(postCreated);
    }

    @Override
    public PackageDto getPackageByPriceId(PackageEntity packageCreated) {
        PriceEntity findPrice = this.priceService.findNearestBeginAtByPackageId(packageCreated.getPackageId());
        return new PackageDto(
                packageCreated.getPackageName(),
                packageCreated.getCaption(),
                packageCreated.getRevision(),
                packageCreated.getDeliveryDay(),
                findPrice.getUnitPrice(),
                findPrice.getPriceId().getBeginAt()
        );
    }


}


