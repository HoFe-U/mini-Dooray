package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.data.response.UserResponse;
import com.nhnacademy.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/", "/index"})
public class HomeController {
    private final UserService service;

    @GetMapping
    public String home(Principal principal,
                       Model model) {
        if(Objects.nonNull(principal)){
            UserResponse response = service.getUser(principal.getName());

            model.addAttribute("user", response);
        }
        else{
            UserResponse userResponse = new UserResponse(null,null,null,"guest");
            model.addAttribute("user",userResponse);
        }

        return "index";
    }
}
