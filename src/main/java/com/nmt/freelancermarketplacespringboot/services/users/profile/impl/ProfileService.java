package com.nmt.freelancermarketplacespringboot.services.users.profile.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.profile.IProfileAttributeRepository;
import com.nmt.freelancermarketplacespringboot.repositories.users.profile.IProfileRepository;
import com.nmt.freelancermarketplacespringboot.repositories.users.profile.IProfileValueRepository;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileService extends AbstractBaseService<ProfileEntity, Integer> implements IProfileService {


    @Autowired
    IProfileAttributeRepository profileAttributeRepository;

    @Autowired
    IProfileValueRepository profileValueRepository;


    public ProfileService(IProfileRepository profileRepository) {
        super(profileRepository);
    }


    /**
     * 1. create Profile
     * 2. create Profile Attribute
     * 3. create Profile Value
     *
     * @param data
     * @param email
     * @return
     */
    public ProfileEntity createOne(CreateProfileDto data, String email){

        /*
         * 1. for loop json array
         * 2. if element [
                {
                  "profileAttributeId": 1, -> skill
                  "profileValues": [
                  * "skill 1",
                  * "skill 2",
                  * "skill 3"
                  *]
                },
                {
                  "profileValueId": 2, -> education
                  "profileValues": [
                  * "education 1"
                  * ]
                }
                {
                  "profileValueId": 3, -> experience
                  "profileValues": [
                  * "experience 1",
                  * "experience 2"
                  * ]
                }
            ]
            * 3. get "profileAttributeId" -> Set
            * 4. new ProfileValue() -> create many profile values with profileId and profileAttributeId
            * 5. return CreateProfileDto
            *
         */
        

        return null;
    }
}