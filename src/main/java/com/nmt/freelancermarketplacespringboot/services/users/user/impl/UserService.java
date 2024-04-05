package com.nmt.freelancermarketplacespringboot.services.users.user.impl;

import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.users.UserExceptionMessages;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.nmt.freelancermarketplacespringboot.common.exceptions.messages.users.UserExceptionMessages.USERNAME_NOT_FOUND;

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
        return this.userRepository.save(findUserByEmail);
        // return this.updateOneById(findUserByEmail.getUserId(), findUserByEmail);
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
    public UserEntity createProfile(String email, CreateProfileDto data) {

        UserEntity findUser = this.getUserByEmail(email);
        if (findUser == null){
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND.getMessage());
        }

        ProfileEntity newProfile =  this.profileService.createOne(data);

        findUser.setProfile(newProfile);


        System.out.println("newProfile: " + newProfile.toString());

        return this.userRepository.save(findUser);
    }

    /**
     * similar create Profile
     * -> customize -> only update field edited
     * @param email
     * @param data
     * @return
     */
    @Override
    public UserEntity updateProfile(String email, CreateProfileDto data) {
        UserEntity findUser = this.getUserByEmail(email);
        if (findUser == null){
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND.getMessage());
        }

        ProfileEntity newProfile =  this.profileService.createOne(data);

        findUser.setProfile(newProfile);
        // save value
        System.out.println("updateProfile: " + newProfile.toString());

        return this.userRepository.save(findUser);
    }

    /**
     *
     * @param email
     * @param data
     * @return
     */
    @Override
    public UserPaymentEntity createUserPayment(String email, CreateUserPaymentDto data) {

        UserEntity checkUser = this.getUserByEmail(email);
        if (checkUser == null){
            throw new UsernameNotFoundException("Username Not Found!");
        }

        try {
            UserPaymentEntity createUserPayment = new UserPaymentEntity();
            createUserPayment.setCardNumber(data.cardNumber());
            createUserPayment.setCardholderName(data.cardholderName());
            createUserPayment.setCvv(data.cvv());
            createUserPayment.setExpired(data.expired());
            createUserPayment.setCountry(data.country());

            checkUser.setUserPayment(createUserPayment);
            this.userRepository.save(checkUser);


            // UserPaymentEntity created = this.userPaymentService.createOne(createUserPayment, email);
            return this.userPaymentService.createOne(createUserPayment);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Failed to create user payment");
        }
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
            throw new RuntimeException(
                    UserExceptionMessages.USER_NOT_FOUND_OR_EMAIL_INVALID.getMessage());
        }

        return this.userRepository.findByAccountEmail(findAcc.getEmail());
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
    public UserPaymentEntity getUserPaymentByEmail(String email) {
        UserEntity findUser = this.getUserByEmail(email);
        return findUser.getUserPayment();
    }


    @Override
    public Page<UserEntity> getAllUser(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize - 1);
        return this.userRepository.findAll(pageable);
    }

    @Override
    public Page<UserEntity> getUsersIsSellerOrBuyer(Boolean isSeller, int page, int size) {
        if (isSeller) {
            // Query for users where the profile is not null (sellers)
            return userRepository.findByProfileIsNotNull(PageRequest.of(page - 1, size - 1));
        } else {
            // Query for all users (buyers)
            return userRepository.findAll(PageRequest.of(page, size));
        }
    }
}
