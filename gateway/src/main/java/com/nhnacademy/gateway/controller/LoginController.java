package com.nhnacademy.gateway.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value = {"/login","/"})
@RequiredArgsConstructor
public class LoginController {
    private final RestTemplate restTemplate;
    @GetMapping
    public String goLoginPage(){
        return "loginForm.html";
    }
}