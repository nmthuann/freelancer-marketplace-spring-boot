package com.nmt.freelancermarketplacespringboot.services.users.profile.impl;


import com.nmt.freelancermarketplacespringboot.common.enums.ProfileLevelEnum;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileAttributeDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileValueDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileValueEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.profile.IProfileRepository;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileAttributeService;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileService;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileValueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class ProfileService extends AbstractBaseService<ProfileEntity, Integer> implements IProfileService {


    @Autowired
    IProfileAttributeService profileAttributeService;

    @Autowired
    IProfileValueService profileValueService;

    @Autowired
    IProfileRepository profileRepository;


    public ProfileService(IProfileRepository profileRepository) {
        super(profileRepository);
    }


    /**
     * 1. create Profile
     * 2. create Profile Attribute
     * 3. create Profile Value
     *
     * @param data
     * @return
     */
    @Override
    @Transactional
    public ProfileEntity createOne(CreateProfileDto data){

        // check CreateProfileDto is JSON

        ProfileEntity newProfile = new ProfileEntity();
        newProfile.setOccupation(data.occupation());
        newProfile.setLevel(ProfileLevelEnum.NEW_SELLER.getLevel());

        // fix error JSon -> fix in Entity file
        ProfileEntity profileCreated = this.profileRepository.save(newProfile);
        // super.createOne(newProfile); // -> save newProfile


        // create Transaction
        Set<ProfileValueEntity>  profileValueEntities = new HashSet<>();

        for (CreateProfileAttributeDto attribute : data.profileAttributes()) {
            System.out.println(data.profileAttributes());

            for (CreateProfileValueDto value: attribute.profileValues()){

                // data.profileAttributes().iterator().next().profileValues()
                ProfileValueEntity profileValueEntity = new ProfileValueEntity();

                profileValueEntity.setProfile(profileCreated);

                profileValueEntity.setProfileAttribute(
                        this.profileAttributeService.getOneById(attribute.profileAttributeId()));
                profileValueEntity.setProfileValue(value.profileValue());

                ProfileValueEntity profileValueCreated = this.profileValueService.createOne(profileValueEntity);

                profileValueEntities.add(profileValueCreated);
            }

            this.profileValueService.saveAll(profileValueEntities);

        }
        // this.profileValueService.saveAll(profileValueEntities);
        return profileCreated;
    }


    @Override
    public ProfileDto getProfileById(Integer id) {
        return null;
    }
}
        /*
         * 1. for loop json array
         * 2. if element
         * {
         *      "occupation": "occupation ABC",
         *      "level": "NEW SELLER",
         *      "profileAttributes": [
                   {
                      "profileAttributeId": 792, -> skill
                      "profileValues": [
                          * "skill 1",
                          * "skill 2",
                          * "skill 3"
                      *]
                    },
                    {
                      "profileAttributeId": 4, -> education
                      "profileValues": [
                          * "education 1"
                      * ]
                    }
                    {
                      "profileAttributeId": 246, -> experience
                      "profileValues": [
                          * "experience 1",
                          * "experience 2"
                      * ]
                    }
               ]
         * }
            * 3. get "profileAttributeId" -> Set
            * 4. new ProfileValue() -> create many profile values with profileId and profileAttributeId
            * 5. return CreateProfileDto
            *
         */
