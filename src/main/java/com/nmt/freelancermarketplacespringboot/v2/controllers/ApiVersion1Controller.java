package com.nmt.freelancermarketplacespringboot.v2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiVersion1Controller {
    @GetMapping("/users")
    public String getHello(){
        return "API Version 1 is is maintaining ...";
    }
}
