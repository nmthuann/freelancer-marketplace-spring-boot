package com.nmt.freelancermarketplacespringboot.services.posts.major;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorValueEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileValueEntity;

import java.util.Set;

public interface IMajorValueService extends IBaseService<MajorValueEntity, Integer> {

    void saveAll(Set<MajorValueEntity> majorValues);
}
