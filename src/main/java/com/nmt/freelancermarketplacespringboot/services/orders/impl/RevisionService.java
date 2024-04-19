package com.nmt.freelancermarketplacespringboot.services.orders.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.orders.RevisionEntity;
import com.nmt.freelancermarketplacespringboot.repositories.orders.IRevisionRepository;
import com.nmt.freelancermarketplacespringboot.services.orders.IReasonService;
import com.nmt.freelancermarketplacespringboot.services.orders.IRevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RevisionService extends AbstractBaseService<RevisionEntity, Long> implements IRevisionService {


    @Autowired
    public RevisionService(IRevisionRepository revisionRepository) {
        super(revisionRepository);
    }
}
