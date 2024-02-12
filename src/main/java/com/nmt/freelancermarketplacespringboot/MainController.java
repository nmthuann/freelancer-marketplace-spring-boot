package com.nmt.freelancermarketplacespringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String getHello(){
        return "Hello World!";
    }
}



