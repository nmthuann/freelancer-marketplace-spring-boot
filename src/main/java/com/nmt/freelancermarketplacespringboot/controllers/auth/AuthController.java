package com.nmt.freelancermarketplacespringboot.controllers.auth;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;

import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterResultDto;
import com.nmt.freelancermarketplacespringboot.services.auth.IAuthService;

import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService authService;
//    @Autowired
//    public AuthController(IAuthService authService) {
//        this.authService = authService;
//    }


    @PostMapping("/user/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginDto data) throws AuthException {
        Tokens tokens = this.authService.login(data);
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }


    @PostMapping("/user/register")
    public ResponseEntity<?> register (@Valid @RequestBody RegisterDto data) {
        System.out.println("REGISTER.....");
        RegisterResultDto result = this.authService.register(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/user/verify-email")
    public ResponseEntity<?> verifyEmail (@Valid @RequestBody String email) {
        System.out.println("VERIFY EMAIL.....");
        String result = this.authService.verifyEmail(email);
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