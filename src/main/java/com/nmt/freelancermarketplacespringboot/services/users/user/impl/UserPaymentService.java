package com.nmt.freelancermarketplacespringboot.services.users.user.impl;


import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;

import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.user.IUserPaymentRepository;

import com.nmt.freelancermarketplacespringboot.services.users.user.IUserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserPaymentService
        extends AbstractBaseService<UserPaymentEntity, UUID>
        implements IUserPaymentService {


    @Autowired
    public UserPaymentService(IUserPaymentRepository userPaymentRepository) {
        super(userPaymentRepository);
    }



    public UserPaymentEntity getUserPaymentByEmail(String email){
        return null;
    }
}
