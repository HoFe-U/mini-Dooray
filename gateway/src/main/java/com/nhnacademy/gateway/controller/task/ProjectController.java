package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.data.request.ProjectRegisterRequest;
import com.nhnacademy.gateway.data.response.*;
import com.nhnacademy.gateway.data.request.ProjectRegister;
import com.nhnacademy.gateway.service.MemberService;
import com.nhnacademy.gateway.service.ProjectService;

import java.security.Principal;
import java.util.List;

import com.nhnacademy.gateway.service.TaskService;

import javax.validation.Valid;
import javax.validation.ValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {
    private final ProjectService projectService;
    private final MemberService memberService;

    private final TaskService taskService;

    @GetMapping("/register")
    public String createProject() {
        return "project/projectRegisterForm";
    }

    @GetMapping("/view")
    public String projectListView(@RequestParam("member") String id,
                                  Model model) {

        List<ResponseProjectList> projectList = projectService.getProjectList(id);
        if (projectList.isEmpty()) {
            List<ResponseProjectList> empty
                    = List.of(new ResponseProjectList(0, "empty", "empty", "empty"));
            model.addAttribute("projects", empty);
        } else {
            model.addAttribute("projects", projectList);
        }
        return "project/projectList";
    }

    @PostMapping("/register")
    public String projectRegister(@ModelAttribute @Valid ProjectRegister form,
                                  BindingResult bindingResult,
                                  Principal principal) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        ProjectRegisterRequest
                request = new ProjectRegisterRequest(principal.getName(), form.getTitle(), form.getContent());

        log.warn("request data check : {}", principal.getName());
        projectService.registerProject(request);

        return "redirect:/project/view?member=" + principal.getName();
    }

    @GetMapping("/info")
    public String projectInfo(@RequestParam("projectNo") Integer projectNo,
                              Model model) {
        ResponseProject project = projectService.getProject(projectNo);
        model.addAttribute("project", project);
        List<ResponseMember> members = memberService.getMembers(projectNo);
        model.addAttribute("members", members);
        List<ResponseMember> pMembers = memberService.getProjectMembers(projectNo);
        model.addAttribute("projectMembers", pMembers);

        return "project/projectInfos";
    }

    @GetMapping("/status/{projectNo}")
    public String fixState(@PathVariable("projectNo") Integer projectNo,
                           @RequestParam("status") String status) {
        projectService.projectModifyState(projectNo, status);
        return "redirect:/project/info?projectNo=" + projectNo;
    }

    @PostMapping("/register/member/{projectNo}")
    public String registerMember(@PathVariable("projectNo") Integer projectNo,
                                 @RequestParam("memberNo") Integer memberNo){

        projectService.projectMemberRegister(projectNo, memberNo);
        return "redirect:/project/info?projectNo=" + projectNo;
    }

    @GetMapping("/members/delete/{projectNo}")
    public String deleteMember(@PathVariable("projectNo") String projectNo,
                               @RequestParam("memberNo")String memberNo){
        projectService.projectMemberRemove(projectNo,memberNo);
        return "redirect:/project/info?projectNo=" + projectNo;
    }

}
