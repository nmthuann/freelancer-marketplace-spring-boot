package com.nmt.freelancermarketplacespringboot.controllers.users.profile;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseController;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileAttributeEntity;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public/profileAttribute")
public class ProfileAttributeController extends AbstractBaseController<ProfileAttributeEntity, Integer> {


    //IProfileAttributeService profileAttributeService;

    @Autowired
    public ProfileAttributeController(IProfileAttributeService profileAttributeService) {
        super(profileAttributeService);
        //this.profileAttributeService = profileAttributeService;
    }

    /*
     * Ex:
     * 1. education,
     * 2. skill,
     * 3. experience
     */
}
