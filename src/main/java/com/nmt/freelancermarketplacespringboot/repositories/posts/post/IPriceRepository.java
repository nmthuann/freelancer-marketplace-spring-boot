package com.nmt.freelancermarketplacespringboot.repositories.posts.post;

import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceIdEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPriceRepository extends CrudRepository<PriceEntity, PriceIdEntity> {
    @Query("SELECT p FROM PriceEntity p WHERE p.priceId.packageEntity.packageId = :packageId " +
            "ORDER BY ABS(DATEDIFF(p.priceId.beginAt, CURRENT_DATE)) ASC")
    PriceEntity findNearestBeginAtByPackageId(@Param("packageId") int packageId);



}

