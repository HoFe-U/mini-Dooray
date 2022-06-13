package com.nhnacademy.task.controller;

import com.nhnacademy.task.data.response.ProjectResponse;
import com.nhnacademy.task.data.response.ProjectView;
import com.nhnacademy.task.data.vo.ProjectMemberRequestVo;
import com.nhnacademy.task.data.vo.ProjectRequestVo;
import com.nhnacademy.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/view/{userId}")
    public List<ProjectView> getProjects(@PathVariable("userId") String userId) {
       return projectService.getProjects(userId);
    }

    @PostMapping("/register")
    public void createProject(@RequestBody @Valid ProjectRequestVo projectVo,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }
        projectService.registerProject(projectVo);
    }

    @GetMapping("/get")
    public ProjectResponse getProject(@RequestParam("projectNo") Integer projectNo) {
        return projectService.getProject(projectNo);
    }

    @PutMapping("/status/{projectNo}")
    public void modifyProjectState(@PathVariable("projectNo") Integer projectNo,
                                   @RequestParam("status") String status){
        projectService.modifyState(projectNo,status);
    }

    @PostMapping("/register/member")
    public void createProjectMember(@RequestBody @Valid ProjectMemberRequestVo projectMemberRequestVo,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new ValidationException();
        }

        projectService.registerProjectMember(projectMemberRequestVo);
    }

    @DeleteMapping("/delete/member/{projectNo}")
    public void deleteProjectMember(@PathVariable("projectNo") Integer projectNo,
                                    @RequestParam("memberNo") Integer memberNo){
        projectService.deleteProjectMember(projectNo, memberNo);
    }
}
