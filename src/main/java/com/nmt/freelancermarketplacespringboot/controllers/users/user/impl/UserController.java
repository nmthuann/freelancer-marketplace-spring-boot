package com.nmt.freelancermarketplacespringboot.controllers.users.user.impl;


import com.nmt.freelancermarketplacespringboot.controllers.users.user.IUserController;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserDto;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


/**
 * - 1. Create Profile (Seller) -> OK
 * - 2. Create User Payment(Seller) -> OK
 * - 3. Update User Information (Seller - Buyer)
 * - 4. Get User Is Seller
 * - 5. Get User Is Buyer
 * - 6. Get User By Email
 * - 7. Get Profile By Email -> NO
 * - 8. Get User Payment By Email
 * =>  Call API in User Controller affect to handle User Service too much code line.
 */
@RestController
@RequestMapping("/users")
public class UserController implements IUserController {


    @Autowired
    IUserService userService;

//    @Autowired
//    UserDetails userDetails;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;

    }

    @PutMapping("/update")
    @Override
    public ResponseEntity<UserEntity> updateUserInformation(HttpServletRequest request, UserDto data) {
        UserEntity result = this.userService.updateUserInformation("userDetails.getUsername()", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create-profile")
    @Override
    public ResponseEntity<ProfileDto> createProfile(HttpServletRequest request, CreateProfileDto data) {
        ProfileDto result = this.userService.createProfile("userDetails.getUsername()", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update-profile")
    @Override
    public ResponseEntity<ProfileDto> updateProfile(HttpServletRequest request, CreateProfileDto data) {
        return null;
    }

    @PostMapping("/create-user-payment")
    @Override
    public ResponseEntity<UserPaymentEntity> createUserPayment(HttpServletRequest request, CreateUserPaymentDto data) {
        return null;
    }

    @GetMapping("/get-user")
    @Override
    public ResponseEntity<UserEntity> getUserByEmail(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-profile")
    @Override
    public ResponseEntity<ProfileDto> getProfileByEmail(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-user-payment")
    @Override
    public ResponseEntity<UserPaymentEntity> getUserPaymentByEmail(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-all")
    @Override
    public ResponseEntity<UserEntity> getAllUser(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-sellers")
    @Override
    public ResponseEntity<UserEntity> getUsersIsSeller(HttpServletRequest request) {
        return null;
    }

    @GetMapping("/get-buyers")
    @Override
    public ResponseEntity<UserEntity> getUsersIsBuyer(@NonNull HttpServletRequest request) {
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