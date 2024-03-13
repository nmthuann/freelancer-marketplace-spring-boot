package com.nmt.freelancermarketplacespringboot.services.users.user.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileAttributeDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserPaymentDto;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.user.IUserRepository;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import com.nmt.freelancermarketplacespringboot.services.users.profile.IProfileService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserPaymentService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractBaseService<UserEntity, UUID> implements IUserService {

    @Autowired
    IUserPaymentService userPaymentService;

    @Autowired
    IProfileService profileService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IUserRepository userRepository;


    public UserService(IUserRepository userRepository) {
        super(userRepository);
    }

    /**
     * 1. find User by Acc
     * 2. setUser with UserDto data
     * 3. save
     * 4. return
     * @param email
     * @param data
     * @return
     */
    @Override
    public UserEntity updateUserInformation(String email, UserDto data) {
        UserEntity findUserByEmail = this.getUserByEmail(email);
        findUserByEmail.setLastName(data.firstName());
        findUserByEmail.setFirstName(data.firstName());
        findUserByEmail.setPhone(data.phone());
        findUserByEmail.setBirthday(data.birthday());
        findUserByEmail.setGender(data.gender());
        findUserByEmail.setAvatarURL(data.avatarURL());
        findUserByEmail.setLocation(data.location());

        return this.updateOneById(findUserByEmail.getUserId(), findUserByEmail);
    }

    /**
     * 1. check Acc
     * 2. create Profile
     * 3. mapping result to Dto
     * @param email
     * @param data
     * @return
     */
    @Override
    public ProfileDto createProfile(String email, CreateProfileDto data) {

        AccountEntity checkAccount = this.accountService.getOneById(email);
        if (checkAccount == null){
            return null;
        }

        ProfileEntity newProfile =  this.profileService.createOne(data);
        UserEntity getUserByEmail = this.getUserByEmail(email);
        getUserByEmail.setProfile(newProfile);
        // save value
        Set<ProfileAttributeDto> attributes = null;


        return new ProfileDto(email,
                getUserByEmail.getLastName(),
                getUserByEmail.getFirstName(),
                getUserByEmail.getProfile().getLevel(),
                getUserByEmail.getProfile().getOccupation(),
                null );
    }

    /**
     * similar create Profile
     * -> customize -> only update field edited
     * @param email
     * @param data
     * @return
     */
    @Override
    public ProfileDto updateProfile(String email, CreateProfileDto data) {
        // AccountEntity checkAccount = this.accountService.getOneById(email);
        UserEntity getUserByEmail = this.getUserByEmail(email);
        if ((getUserByEmail.getAccount() == null) && (getUserByEmail.getProfile() == null)){
            return null;
        }

        ProfileEntity newProfile =  this.profileService.createOne(data);

        getUserByEmail.setProfile(newProfile);
        // save value
        Set<ProfileAttributeDto> attributes = null;


        return new ProfileDto(email,
                getUserByEmail.getLastName(),
                getUserByEmail.getFirstName(),
                getUserByEmail.getProfile().getLevel(),
                getUserByEmail.getProfile().getOccupation(),
                null );
    }

    /**
     *
     * @param email
     * @param data
     * @return
     */
    @Override
    public UserPaymentEntity createUserPayment(String email, CreateUserPaymentDto data) {
        AccountEntity checkAccount = this.accountService.getOneById(email);
        if (checkAccount == null){
            return null;
        }
        UserPaymentEntity createUserPayment = new UserPaymentEntity();
        createUserPayment.setCardNumber(data.cardNumber());
        createUserPayment.setCardholderName(data.cardholderName());
        createUserPayment.setCvv(data.cvv());
        createUserPayment.setExpired(data.expired());
        createUserPayment.setCountry(data.country());

        // UserPaymentEntity created = this.userPaymentService.createOne(createUserPayment, email);
        return this.userPaymentService.createOne(createUserPayment);
    }

    /**
     * 1. need check findByEmail in IUserRepository
     * @param email
     * @return
     */
    @Override
    public UserEntity getUserByEmail(String email) {

        AccountEntity findAcc = this.accountService.getOneById(email);
        if (findAcc == null || !findAcc.isStatus()){
            throw new RuntimeException("Not Found Email or Email Invalid");
        }

        return this.userRepository.findByEmail(findAcc.getEmail());
    }

    /**
     * No Complete!
     * @param email
     * @return
     */
    @Override
    public ProfileDto getProfileByEmail(String email) {
        UserEntity findUser = this.getUserByEmail(email);
        return new ProfileDto(
                findUser.getAccount().getEmail(),
                findUser.getLastName(),
                findUser.getLastName(),
                findUser.getProfile().getOccupation(),
                findUser.getProfile().getLevel(),
                null

        );
    }

    @Override
    public UserPaymentEntity getUserPaymentIsEmail(String email) {
        UserEntity findUser = this.getUserByEmail(email);
        return findUser.getUserPayment();
    }


    @Override
    public Page<UserEntity> getAllUser(Integer pageNumber, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return this.userRepository.findAll(pageable);
    }

    @Override
    public Page<UserEntity> getUsersIsSellerOrBuyer(Boolean isSeller, int page, int size) {
        if (isSeller) {
            // Query for users where the profile is not null (sellers)
            return userRepository.findByProfileIsNotNull(PageRequest.of(page, size));
        } else {
            // Query for all users (buyers)
            return userRepository.findAll(PageRequest.of(page, size));
        }
    }
}
