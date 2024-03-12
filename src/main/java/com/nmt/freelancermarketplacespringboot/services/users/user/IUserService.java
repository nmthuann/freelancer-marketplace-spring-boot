package com.nmt.freelancermarketplacespringboot.services.users.user;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserPaymentDto;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface IUserService extends IBaseService<UserEntity, UUID> {
    ProfileDto updateUserInformation (String email, CreateProfileDto data);
    ProfileDto createProfile (String email, CreateProfileDto data);
    ProfileDto updateProfile (String email, CreateProfileDto data);
    CreateUserPaymentDto createUserPayment (String email, CreateUserPaymentDto data);
    UserDto getUserByEmail (String email);
    ProfileDto getProfileByEmail (String email);
    UserPaymentDto getUserPaymentIsEmail  (String email);
    void getAllUser(Integer pageNumber, Integer pageSize ); // keyword, sort ...
    void getUsersIsSellerOrBuyer(Boolean isSeller);

}
