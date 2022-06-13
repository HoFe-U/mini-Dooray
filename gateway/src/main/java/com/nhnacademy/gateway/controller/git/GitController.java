package com.nhnacademy.gateway.controller.git;

import com.nhnacademy.gateway.data.git.State;
import com.nhnacademy.gateway.service.CustomGitLoginService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GitController {
    private final CustomGitLoginService service;

    @GetMapping("/oauth2/authorization/github")
    public void readyGitLogin(HttpServletResponse response) {
        try {
            State stateCookie = service.gitRequest();
            response.sendRedirect(stateCookie.getUrl());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
