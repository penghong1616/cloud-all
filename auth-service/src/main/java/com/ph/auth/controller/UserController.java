package com.ph.auth.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }
    @GetMapping("/hello")
    public  String hello(){
        return "Hello,Security";
    }
    @GetMapping("/auth")
    public  String auth(){
        return "auth";
    }
}
