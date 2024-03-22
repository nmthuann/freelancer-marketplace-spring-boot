package com.nmt.freelancermarketplacespringboot.services.posts.major.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorValueEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.major.IMajorValueRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorValueService
        extends AbstractBaseService<MajorValueEntity, Integer>
        implements IMajorValueService {

    @Autowired
    IMajorValueRepository majorValueRepository;

    @Autowired
    public MajorValueService(IMajorValueRepository majorValueRepository) {
        super(majorValueRepository);
    }
}
