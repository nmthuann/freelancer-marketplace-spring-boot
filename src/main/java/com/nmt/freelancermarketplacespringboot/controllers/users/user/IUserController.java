package com.nmt.freelancermarketplacespringboot.controllers.users.user;


import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
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

    ResponseEntity<ProfileDto> updateUserInformation (HttpServletRequest request, CreateProfileDto data);

    ResponseEntity<ProfileDto> createProfile (HttpServletRequest request, CreateProfileDto data);

    ResponseEntity<ProfileDto> updateProfile (HttpServletRequest request, CreateProfileDto data);

    ResponseEntity<?> createUserPayment (CreateUserPaymentDto data, HttpServletRequest request);


    ResponseEntity<?> getUserByEmail (HttpServletRequest request);

    ResponseEntity<?> getProfileByEmail (HttpServletRequest request);

    ResponseEntity<?> getUserPaymentIsEmail ( HttpServletRequest request);

    ResponseEntity<?> getAllUser (HttpServletRequest request);

    ResponseEntity<?> getUsersIsSeller (HttpServletRequest request);

    ResponseEntity<?> getUsersIsBuyer (HttpServletRequest request);
}
