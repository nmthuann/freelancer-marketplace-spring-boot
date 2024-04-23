package com.nmt.freelancermarketplacespringboot.controllers;

import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String getHello(){
        return "Hello World!";
    }

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck () {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}



