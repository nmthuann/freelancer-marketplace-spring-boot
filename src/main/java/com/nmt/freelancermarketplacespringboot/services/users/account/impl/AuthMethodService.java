package com.nmt.freelancermarketplacespringboot.services.users.account.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AuthMethodEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.account.IAuthMethodRepository;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAuthMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthMethodService
        extends AbstractBaseService <AuthMethodEntity, Integer>
        implements IAuthMethodService {

//    @Autowired
//    IAuthMethodRepository authMethodRepository;
//
//    public AuthMethodService(IAuthMethodRepository authMethodRepository) { //CrudRepository<AuthMethodEntity, Integer> baseRepository
//        super(authMethodRepository);
//    }

    @Autowired
    public AuthMethodService(IAuthMethodRepository authMethodRepository) {
        super(authMethodRepository);
    }
}
