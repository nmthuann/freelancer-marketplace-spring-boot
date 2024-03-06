package com.nmt.freelancermarketplacespringboot.controllers.auth;


import com.nmt.freelancermarketplacespringboot.common.utils.JwtServiceUtil;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.dto.users.account.CreateAccountDto;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.services.auth.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    private final UserDetailsService userDetailsService;

    @Autowired
    JwtServiceUtil jwtService;

    public AuthController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/user/login")
    public ResponseEntity<?> login (@RequestBody LoginDto data) {
        try {
            Tokens tokens = this.authService.login(data).get();
            return new ResponseEntity<Tokens>(tokens, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> register (@NonNull HttpServletRequest request, @RequestBody LoginDto data) {
        System.out.println("REGISTER");
        final String token = request.getHeader("Authorization").substring(7);
        String username =
                this.jwtService.extractUsername(token);
        System.out.println("username::: " +username);
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
        boolean checkExp = this.jwtService.isTokenValid(token, userDetails);
        System.out.println("checkExp::: " + checkExp);
        return new ResponseEntity<>("REGISTER", HttpStatus.OK);
    }
}
