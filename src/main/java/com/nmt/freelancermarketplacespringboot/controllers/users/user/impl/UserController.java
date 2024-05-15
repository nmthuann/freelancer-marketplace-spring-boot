package com.nmt.freelancermarketplacespringboot.controllers.users.user.impl;


import com.nmt.freelancermarketplacespringboot.dto.users.profile.CreateProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.profile.ProfileDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.dto.users.user.UserDto;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


/**
 * - 1. Create Profile (Seller) -> OK
 * - 2. Create User Payment(Seller) -> OK
 * - 3. Update User Information (Seller - Buyer) -> OK
 * - 4. Get User By Email -> OK
 * - 5. Get Profile By Email -> NO
 * - 6. Get User Payment By Email
 * - 7. Get User Is Seller (Page)
 * - 8. Get User Is Buyer (Page)
 * - 9. Get Users (Page)
 * =>  Call API in User Controller affect to handle User Service too much code line.
 */
@RestController
@RequestMapping("/users")
public class UserController { // implements IUserController


    // @Autowired
    private final IUserService userService;


    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }



    @PostMapping("/create-profile")
    public ResponseEntity<?> createProfile (
            @Valid @RequestBody CreateProfileDto data,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        System.out.println(data.toString());
        UserEntity result = this.userService.createProfile(userDetails.getUsername(), data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile (
            @Valid @RequestBody CreateProfileDto data,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserEntity result = this.userService.updateProfile(userDetails.getUsername(), data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create-user-payment")
    public ResponseEntity<UserPaymentEntity> createUserPayment (
            @Valid @RequestBody CreateUserPaymentDto data,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserPaymentEntity result = this.userService.createUserPayment(userDetails.getUsername(), data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<UserEntity> updateUserInformation (
            @Valid @RequestBody UserDto data,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserEntity result = this.userService.updateUserInformation(userDetails.getUsername(), data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/get-user")
    public ResponseEntity<UserEntity> getUserByEmail(@AuthenticationPrincipal UserDetails userDetails) {
        UserEntity result = this.userService.getUserByEmail(userDetails.getUsername());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get-profile")
    public ResponseEntity<ProfileDto> getProfileByEmail(@AuthenticationPrincipal UserDetails userDetails) {
        ProfileDto result = this.userService.getProfileByEmail(userDetails.getUsername());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get-user-payment")
    public ResponseEntity<UserPaymentEntity> getUserPaymentByEmail(@AuthenticationPrincipal UserDetails userDetails) {
        UserPaymentEntity result = this.userService.getUserPaymentByEmail(userDetails.getUsername());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUser(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Student> studentPage = studentRepository.findAll(pageable);
        Page<UserEntity> result = this.userService.getAllUser(page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get-sellers") //  require TOKEN
    public ResponseEntity<?> getUsersIsSeller (
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<UserEntity> result = this.userService.getUsersIsSellerOrBuyer(true, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get-buyers") //  require TOKEN # get All User
    public ResponseEntity<?> getUsersIsBuyer(
            @RequestParam int page, // careful the default value.
            @RequestParam int size
    ) {
        Page<UserEntity> result = this.userService.getUsersIsSellerOrBuyer(false, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
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