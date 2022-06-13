package com.nhnacademy.task.controller;

import com.nhnacademy.task.data.response.ResponseMember;
import com.nhnacademy.task.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/get/notIn")
    public List<ResponseMember> findMembersInProject(@RequestParam("projectNo") Integer projectNo) {
       return memberService.getMembersNotInProject(projectNo);
    }

    @GetMapping("/get/in")
    public List<ResponseMember> findMembersInfoProject(@RequestParam("projectNo") Integer projectNo) {
        return memberService.getMemberInProject(projectNo);
    }
    @PostMapping("/register")
    public void registerMember(@RequestParam("id") String id){
        memberService.registerMember(id);
    }


}
