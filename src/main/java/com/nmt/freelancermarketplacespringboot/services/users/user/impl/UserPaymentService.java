package com.nmt.freelancermarketplacespringboot.services.users.user.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.user.IUserPaymentRepository;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserPaymentService;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public class UserPaymentService
        extends AbstractBaseService<UserPaymentEntity, UUID>
        implements IUserPaymentService {

    public UserPaymentService(IUserPaymentRepository userPaymentRepository) {
        super(userPaymentRepository);
    }
}
