package com.example.mybookshopapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninPageController {

    @GetMapping("/signin")
    public String signinPage(){
        return "signin";
    }
}