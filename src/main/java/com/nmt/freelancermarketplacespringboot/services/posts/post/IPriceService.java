package com.nmt.freelancermarketplacespringboot.services.posts.post;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceIdEntity;
import org.springframework.data.repository.query.Param;

public interface IPriceService extends IBaseService<PriceEntity, PriceIdEntity> {
    PriceEntity findNearestBeginAtByPackageId(int packageId);
}
