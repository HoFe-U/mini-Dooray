package com.nhnacademy.gateway.controller;


import com.nhnacademy.gateway.adaptor.MemberAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String goLoginPage() {
        return "login";
    }
}