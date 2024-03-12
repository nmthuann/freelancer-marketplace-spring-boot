package com.nmt.freelancermarketplacespringboot.services.users.user.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserPaymentDto;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.user.IUserRepository;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserPaymentService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends AbstractBaseService<UserEntity, UUID> implements IUserService {

    @Autowired
    IUserPaymentService userPaymentService;

    @Autowired
    IProfileService profileService;

    @Autowired
    IAccountService accountService;



    public UserService(IUserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public ProfileDto updateUserInformation(String email, CreateProfileDto data) {
        return null;
    }

    @Override
    public ProfileDto createProfile(String email, CreateProfileDto data) {
        return null;
    }

    @Override
    public ProfileDto updateProfile(String email, CreateProfileDto data) {
        return null;
    }

    @Override
    public CreateUserPaymentDto createUserPayment(String email, CreateUserPaymentDto data) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public ProfileDto getProfileByEmail(String email) {
        return null;
    }

    @Override
    public UserPaymentDto getUserPaymentIsEmail(String email) {
        return null;
    }

    @Override
    public void getAllUser(Integer pageNumber, Integer pageSize) {

    }

    @Override
    public void getUsersIsSellerOrBuyer(Boolean isSeller) {

    }
}
