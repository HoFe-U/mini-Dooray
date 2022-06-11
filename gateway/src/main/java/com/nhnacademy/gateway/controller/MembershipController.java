package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.adaptor.MemberAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/membership")
public class MembershipController {
    private final MemberAdaptor memberAdaptor;

    @GetMapping
    public String doMembership(){
        return "/membership";
    }

    @PostMapping
    public String doMembershipRegistration(@RequestParam("id") String id,
                                           @RequestParam("pwd") String pwd,
                                           @RequestParam("email") String email,
                                           @RequestParam("name") String name) {
        memberAdaptor.joinMember(id,name, pwd, email);
        return "redirect:/login";
    }
}
