package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PriceIdEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IPriceRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceService extends AbstractBaseService <PriceEntity, PriceIdEntity> implements IPriceService {

    @Autowired
    public PriceService(IPriceRepository priceRepository) {
        super(priceRepository);
    }
}
