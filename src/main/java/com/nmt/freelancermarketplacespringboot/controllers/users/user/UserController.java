package com.nmt.freelancermarketplacespringboot.controllers.users.user;


import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterResultDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 */
@RestController
@RequestMapping("/users")
public class UserController {



    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateUserInformation (@Valid @RequestBody RegisterDto data) {
        System.out.println("UPDATE USER .....");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail (@Valid @RequestBody RegisterDto data) {
        System.out.println("CREATE USER PAYMENT.....");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/get-users")
    public ResponseEntity<?> getUsers (@Valid @RequestBody RegisterDto data) {
        System.out.println("CREATE USER PAYMENT.....");
        return new ResponseEntity<>("", HttpStatus.OK);
    }




}


/*
 * - create User Payment
 * - update User
 * - get User by Id
 * - get All User -> pagination, ...
 */