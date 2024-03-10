package com.nmt.freelancermarketplacespringboot.services.users.profile.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.profile.IProfileRepository;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileService;


public class ProfileService extends AbstractBaseService<ProfileEntity, Integer> implements IProfileService {
    public ProfileService(IProfileRepository profileRepository) {
        super(profileRepository);
    }
}