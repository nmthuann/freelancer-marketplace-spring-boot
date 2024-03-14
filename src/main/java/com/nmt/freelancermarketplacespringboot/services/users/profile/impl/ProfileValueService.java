package com.nmt.freelancermarketplacespringboot.services.users.profile.impl;


import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileValueEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.profile.IProfileValueRepository;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileValueService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfileValueService
        extends AbstractBaseService<ProfileValueEntity, Integer>
        implements IProfileValueService {

    @Autowired
    IProfileValueRepository profileValueRepository;


    @Autowired
    public ProfileValueService(IProfileValueRepository profileValueRepository) {
        super(profileValueRepository);
    }


    @Override
    public void saveAll(Set<ProfileValueEntity> profileValues) {
        profileValueRepository.saveAll(profileValues);
    }


    @Override
    public List<ProfileValueEntity> findByProfileProfileId(Integer profileId) {
        return this.profileValueRepository.findByProfileProfileId(profileId);
    }



}
