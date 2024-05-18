package com.nmt.freelancermarketplacespringboot.v2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionApiController {
    @GetMapping("/api/v2")
    public String getHello(){
        return "API Version 2 is is maintaining ...";
    }
}
