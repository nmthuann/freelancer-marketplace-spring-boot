package com.nmt.freelancermarketplacespringboot.controllers.auth;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.common.utils.JwtServiceUtil;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.services.auth.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
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



    @PostMapping("/user/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginDto data) throws AuthException {
        Tokens tokens = this.authService.login(data);
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }


    @PostMapping("/user/register")
    public ResponseEntity<?> register (@Valid @RequestBody LoginDto data) {
        System.out.println("REGISTER.....");

        return new ResponseEntity<>("REGISTER", HttpStatus.OK);
    }
}
