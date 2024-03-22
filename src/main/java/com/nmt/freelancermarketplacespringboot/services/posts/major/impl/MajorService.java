package com.nmt.freelancermarketplacespringboot.services.posts.major.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.major.IMajorRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class MajorService extends AbstractBaseService<MajorEntity, Integer> implements IMajorService {


    @Autowired
    IMajorRepository majorRepository;

    @Autowired
    public MajorService(IMajorRepository majorRepository) {
        super(majorRepository);
    }
}
