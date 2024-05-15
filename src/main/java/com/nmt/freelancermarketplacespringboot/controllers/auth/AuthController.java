package com.nmt.freelancermarketplacespringboot.controllers.auth;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.*;
import com.nmt.freelancermarketplacespringboot.services.auth.IAuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;



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
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/user/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginDto data) throws AuthException {
        System.out.println("LOGIN.....");
        logger.info("POST /user/login" + data.email());
        Tokens tokens = this.authService.login(data);
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }



    @PostMapping("/user/register")
    public ResponseEntity<?> registerSync (@Valid @RequestBody RegisterDto data) throws AuthException {
        System.out.println("REGISTER registerSync.....");
        RegisterResultDto result = this.authService.registerSync(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/user/verify-email/{email}")
    public ResponseEntity<?> verifyEmail (@Valid @PathVariable String email) {
        System.out.println("VERIFY EMAIL.....");
        Boolean result = this.authService.verifyEmail("Test", email);
        if (result){
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }


    // will need access Token -> for logout Method
    @PostMapping("/user/logout")
    public ResponseEntity<?> logout (@AuthenticationPrincipal @NotNull UserDetails userDetails) {
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
    public ResponseEntity<?> changePassword (
            @Valid @RequestBody ChangePasswordDto data
            // @RequestParam String token
    ) {
        System.out.println("CHANGE PASSWORD.....");
        String result = this.authService.changePassword(data.email());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PostMapping("/user/forget-password")
    public ResponseEntity<?> forgetPassword (@Valid @RequestBody String email) {
        System.out.println("FORGET PASSWORD.....");
        String result = this.authService.forgetPassword(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


//    @PostMapping("/admin/registerAsync")
//    public ResponseEntity<?> registerAsync (@Valid @RequestBody RegisterDto data) throws AuthException {
//        System.out.println("REGISTER registerAsync.....");
//        RegisterResultDto result = this.authService.registerAsync(data);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}


/*
    Auth Module:
    - Login OK
    - register -> no test
    - verify email -> no test
    - forgot password -> NO
 */