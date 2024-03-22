package com.nmt.freelancermarketplacespringboot.services.posts.major.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorAttributeEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.major.IMajorAttributeRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorAttributeService
        extends AbstractBaseService<MajorAttributeEntity, Integer>
        implements IMajorAttributeService {

    @Autowired
    public MajorAttributeService(IMajorAttributeRepository majorAttributeRepository) {
        super(majorAttributeRepository);
    }
}
