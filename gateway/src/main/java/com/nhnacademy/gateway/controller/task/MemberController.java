package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService service;

    @PostMapping("/register/{projectNo}")
    public String memberRegister(@PathVariable("projectNo") Integer projectNo,
                                 @RequestParam("id") String id) {

        service.registerMember(projectNo, id);

        return "redirect:/project/info?projectNo=" + projectNo;
    }

    @GetMapping("/delete/{projectNo}")
    public String memberDelete(@PathVariable("projectNo") Integer projectNo,
                               @RequestParam("id") String id) {

        service.deleteMember(projectNo, id);

        return "redirect:/project/info?projectNo=" + projectNo;
    }
}
