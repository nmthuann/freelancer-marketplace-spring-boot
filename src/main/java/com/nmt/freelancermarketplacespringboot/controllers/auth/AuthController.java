package com.nmt.freelancermarketplacespringboot.controllers.auth;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;

import com.nmt.freelancermarketplacespringboot.controllers.posts.post.PostController;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterResultDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.ResultLogoutDto;
import com.nmt.freelancermarketplacespringboot.services.auth.IAuthService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;


/**
 * 1. login
 * 2. register
 * 3. logout
 * 4. verify email
 * 5. forget password
 * 6. change password
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    IAuthService authService;


    @PostMapping("/user/login")
    public ResponseEntity<?> login ( @Valid @RequestBody LoginDto data) throws AuthException {
        System.out.println("LOGIN.....");
        // @AuthenticationPrincipal UserDetails userDetails,
        // System.out.println(userDetails.getUsername());
        logger.info("POST /user/login" + data.toString());

        Tokens tokens = this.authService.login(data);
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }


    @PostMapping("/user/register")
    public ResponseEntity<?> register (@Valid @RequestBody RegisterDto data) throws AuthException {
        System.out.println("REGISTER.....");
        RegisterResultDto result = this.authService.register(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/user/verify-email/{email}")
    public ResponseEntity<?> verifyEmail (@Valid @PathVariable String email) {
        System.out.println("VERIFY EMAIL.....");
        String result = this.authService.verifyEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // will need access Token -> for logout Method
    @PostMapping("/user/logout")
    public ResponseEntity<?> logout (@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("LOGOUT.....");
        String logout = this.authService.logout(userDetails.getUsername());
        ResultLogoutDto result = new ResultLogoutDto(
                userDetails.getUsername(),
                HttpStatus.OK.value(),
                logout,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // will need access Token -> for changePassword Method
    @PostMapping("/user/change-password")
    public ResponseEntity<?> changePassword (@Valid @RequestBody String email) {
        System.out.println("CHANGE PASSWORD.....");
        String result = this.authService.changePassword(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PostMapping("/user/forget-password")
    public ResponseEntity<?> forgetPassword (@Valid @RequestBody String email) {
        System.out.println("FORGET PASSWORD.....");
        String result = this.authService.forgetPassword(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

/*
    Auth Module:
    - Login OK
    - register -> no test
    - verify email -> no test
    - forgot password -> NO
 */