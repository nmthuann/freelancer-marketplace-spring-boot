package com.nmt.freelancermarketplacespringboot.controllers.users.user;


import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserDto;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;


/**
 * - Create Profile (Seller) -> OK
 * - Create User Payment(Seller) -> OK
 * - Update User Information (Seller - Buyer)
 * - Get User Is Seller
 * - Get User Is Buyer
 * - Get User By Email
 * - Get Profile By Email -> NO
 * - Get User Payment By Email
 * =>  Call API in User Controller affect to handle User Service too much code line.
 */
public interface IUserController {

    ResponseEntity<UserEntity> updateUserInformation (HttpServletRequest request, UserDto data);

    ResponseEntity<ProfileDto> createProfile (HttpServletRequest request, CreateProfileDto data);

    ResponseEntity<ProfileDto> updateProfile (HttpServletRequest request, CreateProfileDto data);

    ResponseEntity<UserPaymentEntity> createUserPayment (HttpServletRequest request, CreateUserPaymentDto data);

    ResponseEntity<UserEntity> getUserByEmail (HttpServletRequest request);

    ResponseEntity<ProfileDto> getProfileByEmail (HttpServletRequest request);

    ResponseEntity<UserPaymentEntity> getUserPaymentByEmail ( HttpServletRequest request);

    ResponseEntity<UserEntity> getAllUser (HttpServletRequest request);

    ResponseEntity<UserEntity> getUsersIsSeller (HttpServletRequest request);

    ResponseEntity<UserEntity> getUsersIsBuyer (HttpServletRequest request);
}
