package com.nmt.freelancermarketplacespringboot.services.users.profile;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;

public interface IProfileService extends IBaseService<ProfileEntity, Integer> {

    ProfileDto getProfileById(Integer id);

}
