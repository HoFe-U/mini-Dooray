package com.nhnacademy.gateway.controller.account;

import com.nhnacademy.gateway.data.request.UserRegisterRequest;
import com.nhnacademy.gateway.service.MemberService;
import com.nhnacademy.gateway.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    private final MemberService memberService;
    private final PasswordEncoder encoder;

    @GetMapping("/join")
    public String readyJoin() {

        return "join";
    }

    @PostMapping("/join")
    public String doJoin(@ModelAttribute @Valid UserRegisterRequest request,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }
        request.setPw(encoder.encode(request.getPw()));
        service.registerUser(request);
        memberService.signUp(request);
        return "redirect:/";
    }
}
