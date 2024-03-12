package com.nmt.freelancermarketplacespringboot.controllers.users.user.impl;


import com.nmt.freelancermarketplacespringboot.controllers.users.user.IUserController;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterResultDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


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
@RestController
@RequestMapping("/users")
public class UserController implements IUserController {


    @Autowired
    IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;

    }

    @PutMapping("/update")
    @Override
    public ResponseEntity<ProfileDto> updateUserInformation(HttpServletRequest request, CreateProfileDto data) {

        return null;
    }

    @PostMapping("/create-profile")
    @Override
    public ResponseEntity<ProfileDto> createProfile(HttpServletRequest request, CreateProfileDto data) {
        return null;
    }
    @PutMapping("/update-profile")
    @Override
    public ResponseEntity<ProfileDto> updateProfile(HttpServletRequest request, CreateProfileDto data) {
        return null;
    }

    @PostMapping("/create-user-payment")
    @Override
    public ResponseEntity<?> createUserPayment(CreateUserPaymentDto data, HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-user")
    @Override
    public ResponseEntity<?> getUserByEmail(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-profile")
    @Override
    public ResponseEntity<?> getProfileByEmail(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-user-payment")
    @Override
    public ResponseEntity<?> getUserPaymentIsEmail(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-all")
    @Override
    public ResponseEntity<?> getAllUser(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-sellers")
    @Override
    public ResponseEntity<?> getUsersIsSeller(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-buyers")
    @Override
    public ResponseEntity<?> getUsersIsBuyer(@NonNull HttpServletRequest request) {
        return null;
    }
}


/*
 * - create User Payment
 * - update User
 * - get User by Id
 * - get All User -> pagination, ...
 */



//@PutMapping("/update/{email}")
//public ResponseEntity<?> updateUserInformation (@Valid @RequestBody RegisterDto data) {
//    System.out.println("UPDATE USER .....");
//    return new ResponseEntity<>("", HttpStatus.OK);
//}
//
//@GetMapping("/{email}")
//public ResponseEntity<?> getUserByEmail (@Valid @RequestBody RegisterDto data) {
//    System.out.println("CREATE USER PAYMENT.....");
//    return new ResponseEntity<>("", HttpStatus.OK);
//}
//
//@GetMapping("/get-users")
//public ResponseEntity<?> getUsers (@Valid @RequestBody RegisterDto data) {
//    System.out.println("CREATE USER PAYMENT.....");
//    return new ResponseEntity<>("", HttpStatus.OK);
//}