package com.nhnacademy.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class GitController {

    @GetMapping("/oauth2/authorization/github")
    public void doGitLogin(HttpServletResponse response){

    }
}
