package com.nmt.freelancermarketplacespringboot.services.users.user;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserPaymentDto;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IUserService extends IBaseService<UserEntity, UUID> {
    UserEntity updateUserInformation (String email, UserDto data);
    ProfileDto createProfile (String email, CreateProfileDto data);
    ProfileDto updateProfile (String email, CreateProfileDto data);
    UserPaymentEntity createUserPayment (String email, CreateUserPaymentDto data);
    UserEntity getUserByEmail (String email);
    ProfileDto getProfileByEmail (String email);
    UserPaymentEntity getUserPaymentIsEmail  (String email);
    Page<UserEntity> getAllUser(Integer pageNumber, Integer pageSize ); // keyword, sort ...
    Page<UserEntity> getUsersIsSellerOrBuyer(Boolean isSeller, int page, int size);

}
