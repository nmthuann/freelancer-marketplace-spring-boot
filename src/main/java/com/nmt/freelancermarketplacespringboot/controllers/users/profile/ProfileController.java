package com.nmt.freelancermarketplacespringboot.controllers.users.profile;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public class ProfileController {


    @Autowired
    IProfileService profileService;


    @PostMapping("/create")
    public ResponseEntity<?> createProfile (
            @Valid @RequestBody CreateProfileDto data,
            @NonNull HttpServletRequest request
    ){
        System.out.println("CREATE USER PROFILE.....");

        String email = (String) request.getAttribute("email");



        // must be seller
        // ProfileEntity created = this.profileService.createOne(data, email);

        return new ResponseEntity<>("created", HttpStatus.OK);
    }


}

/*
 * - Create Profile (check User before create)
 * - Update Profile
 * - get Profile By email
 * - get Profiles
 */
