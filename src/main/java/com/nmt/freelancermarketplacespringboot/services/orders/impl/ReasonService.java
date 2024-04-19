package com.nmt.freelancermarketplacespringboot.services.orders.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.orders.ReasonEntity;
import com.nmt.freelancermarketplacespringboot.repositories.orders.IReasonRepository;
import com.nmt.freelancermarketplacespringboot.services.orders.IReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ReasonService extends AbstractBaseService<ReasonEntity, Long> implements IReasonService {


    @Autowired
    public ReasonService(IReasonRepository reasonRepository) {
        super(reasonRepository);
    }
}
