package com.nmt.freelancermarketplacespringboot.v2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class ApiVersion2Controller {
    @GetMapping("/users")
    public String getHello(){
        return "API Version 2 is is maintaining ...";
    }
}
