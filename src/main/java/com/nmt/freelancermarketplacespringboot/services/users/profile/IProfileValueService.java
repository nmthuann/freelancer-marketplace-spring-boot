package com.nmt.freelancermarketplacespringboot.services.users.profile;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;


import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileValueEntity;

import java.util.List;
import java.util.Set;

public interface IProfileValueService extends IBaseService<ProfileValueEntity, Integer> {

    void saveAll(Set<ProfileValueEntity> profileValues);
    List<ProfileValueEntity> findByProfileProfileId(Integer profileId);


}
