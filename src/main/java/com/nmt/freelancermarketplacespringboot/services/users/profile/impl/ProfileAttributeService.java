package com.nmt.freelancermarketplacespringboot.services.users.profile.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileAttributeEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.profile.IProfileAttributeRepository;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileAttributeService
        extends AbstractBaseService<ProfileAttributeEntity, Integer> implements IProfileAttributeService {
    @Autowired
    public ProfileAttributeService(IProfileAttributeRepository profileAttributeRepository) {
        super(profileAttributeRepository);
    }
}
